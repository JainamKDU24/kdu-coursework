import { useDispatch, useSelector } from "react-redux";
import * as styles from "../../styles/stockDetailStyles";
import { createUseStyles } from "react-jss";
import { AppDispatch, RootState } from "../../middleware/stores/Store";
import { useParams } from "react-router-dom";
import { Dropdown } from "./Dropdown";
import { ITransaction, addTransaction } from "../../middleware/slices/transactionSlice";
import io from 'socket.io-client';
import { useEffect, useState } from "react";
import { restructureTime } from "../Portfolio/Portfolio";


interface IBar {
  height: number;
  type: 'Green' | 'Red';
}
interface IuserName{
  Name:string
}

interface ISessionTransaction{
  stock_name: string,
  quantity: number,
  user: string,
  type: string,
  action:string,
  timestamp: string,
  status: string
}

const socket = io('http://localhost:5000'); 
export function StockDetail({Name}:IuserName) {
  const useStyles = createUseStyles({
    quantity: {
      height: "100%",
      width: "100%",
      padding: "0",
      border: "none",
      fontSize: "1.3rem",
      textAlign: "center",
      "&:focus": {
        outline: "none",
        fontSize: "1.3rem",
        textAlign: "center",
      },
    },
  });
  const [transactions, setTransactions] = useState([]);
  const [sessionTransaction, setSessionTransaction] = useState<ISessionTransaction[]>([]);
  const dispatch:AppDispatch = useDispatch();
  const stocksList = useSelector((state: RootState) => state.stocks.data);
  console.log(stocksList);
  const { stock_name } = useParams();
  const currentStock = stocksList.find(
    (stock) => stock.stock_name === stock_name
  );
  
  useEffect(() => {
    socket.emit('joinRoom', currentStock?.stock_name);
  
    socket.on('initialTransactions', (data) => {
      setTransactions(data);
    });
  
    socket.on('newTransaction', (transaction:ITransaction) => {
      setTransactions(prevTransactions => [...prevTransactions, transaction]);
    });
  
    return () => {
      socket.off('initialTransactions');
      socket.off('newTransaction');
      socket.off('userName');
    };
  }, [currentStock]);
  
  const handleSwitchStock = (newStockName:string) => {
    socket.emit('leaveRoom', currentStock?.stock_name);
    socket.emit('joinRoom', newStockName);
    socket.emit('getInitialTransactions', newStockName);
  };
  const [walletBalance, setWalletBalance] = useState<number>(100000);

  const handleBuySell = (action: string) => {
    const stockQuantityInput = document.getElementById("stock-quantity") as HTMLInputElement;
    const stockQuantity = Number(stockQuantityInput?.value) || 0;
    const stockPrice = currentStock!.base_price;
    const transactionAmount = stockQuantity * stockPrice;
  
    if (action === "BUY" && transactionAmount > walletBalance) {
      const failedTransaction: ITransaction = {
        stock_name: currentStock!.stock_name,
        stock_symbol: currentStock!.stock_symbol,
        transaction_price: stockPrice,
        timestamp: new Date().toISOString(),
        status: 'Failed'
      };
  
      dispatch(addTransaction(failedTransaction));
  
      alert("Insufficient balance. Transaction failed.");
      return;
    }
  
    const newTransaction: ITransaction = {
      stock_name: currentStock!.stock_name,
      stock_symbol: currentStock!.stock_symbol,
      transaction_price: stockPrice,
      timestamp: new Date().toISOString(),
      status: 'Passed' 
    };
    const transactionType = action === "BUY" ? "bought" : "sold";
    const transaction:ISessionTransaction = {
      stock_name: currentStock!.stock_name,
      quantity: stockQuantity,
      user: Name,
      type: transactionType,
      action,
      timestamp: restructureTime(new Date().toISOString()),
      status: newTransaction.status 
    };
    socket.emit('newTransaction', transaction);
    setSessionTransaction([transaction,...sessionTransaction]);
    if (action === "BUY") {
      setWalletBalance(prevBalance => prevBalance - transactionAmount);
    } else {
      setWalletBalance(prevBalance => prevBalance + transactionAmount);
    }
    dispatch(addTransaction(newTransaction));
  };

  const [graphData, setGraphData] = useState<IBar[]>([]);
  const [currentPrice, setCurrentPrice] = useState<number | null>(null);
  const [percentage, setPercentage] = useState<number>(0.00);

  useEffect(() => {
    setGraphData([]);
    const generateRandomPriceChange = () => {
      return (Math.random() - 0.6) * 100;
    };

    const currentStock = stocksList.find(
      (stock: any) => stock.stock_name === stock_name
    );

    if (currentStock) {
      setCurrentPrice(currentStock.base_price);
      setPercentage(0.00);

      const priceUpdateInterval = setInterval(() => {
        setCurrentPrice((prevPrice: number | null) => {
          if (prevPrice === null) return null;
          
          const newPriceChange = generateRandomPriceChange();
          const newPrice = prevPrice + newPriceChange;
          setPercentage((newPriceChange / prevPrice) * 100);

          const barType: 'Green' | 'Red' = newPriceChange >= 0 ? 'Green' : 'Red';
          
          const newBar: IBar = { height: Math.abs(newPrice), type: barType };
          setGraphData(prevData => [...prevData, newBar]);

          return newPrice;
        });
      }, 5000);

      return () => clearInterval(priceUpdateInterval);
    }
  }, [stock_name]);

  const [columns, setColumns] = useState(13);
  const [rows, setRows] = useState(4);
  useEffect(() => {
    const totalBars = columns * rows;
    if (graphData.length > totalBars) {
      setColumns(columns + 1);
      setRows(rows + 1);
    }
  }, [graphData, columns, rows]);

  return (
    <main style={styles.main}>
      <div style={styles.leftContainer}>
        <div style={styles.stockInfo}>
          <div style={styles.logoName}>
            <Dropdown currentStock={currentStock!} stocksList={stocksList} handleSwitchStock={handleSwitchStock}  />
          </div>
          <div style={styles.price}>
            Price
            <div style={styles.currentPrice}>
              <span>
              <span style={{ color: percentage !== null && percentage < 0 ? 'red' : 'green' }}>
                {currentPrice !== null ? currentPrice.toFixed(2) : ''}
              </span>
                {percentage !== null && percentage > 0 ? (
                  <span style={{ fontSize: '35px', color: 'green' }}>&#8593;</span>
                ) : percentage !== null && percentage < 0 ? (
                  <span style={{ fontSize: '35px', color: 'red' }}>&#8595;</span>
                ) : null}
              </span>
              </div>
            <div>
              <span style={styles.percentage}>{percentage !== null ? `${percentage.toFixed(2)}%` : 'Loading...'}</span>
            </div>
          </div>
          <div style={styles.quantityContainer}>
            <input
              type="text"
              name="stock-quantity"
              id="stock-quantity"
              placeholder="Enter QTY"
              className={useStyles().quantity}
            />
          </div>
          <div style={styles.buy} onClick={() => handleBuySell("BUY")}>BUY</div>
          <div style={styles.sell} onClick={() => handleBuySell("SELL")}>SELL</div>
        </div>
        <div style={styles.graphContainer}>
            {[...Array(rows)].map((_, rowIndex) => (
              <div key={rowIndex} style={styles.gridRow}>
                {[...Array(columns)].map((_, colIndex) => (
                  <div key={colIndex} style={styles.gridCell}>
                    {graphData
                      .slice((rowIndex * columns + colIndex) * 5, (rowIndex * columns + colIndex + 1) * 5)
                      .map((bar, barIndex) => (
                        <div
                          key={barIndex}
                          style={{
                            ...bar.type === 'Green' ? styles.barGreen : styles.barRed,
                            height: `${bar.height}px`,
                          }}
                        />
                      ))}
                  </div>
                ))}
              </div>
            ))}
        </div>
      </div>
      <div style={styles.rightContainer}>
      <div style={styles.history}>
        <div style={styles.title}>History</div>
        <div style={styles.orders}>
          {sessionTransaction.length > 0 ? (
            sessionTransaction.map((transaction, index) => (
              <div style={styles.order} key={index}>
                <div style={styles.info}>
                  <div style={styles.priceQuantity}>{transaction.quantity} Stocks</div>
                  <div style={styles.time}>{transaction.timestamp}</div>
                </div>
                <div style={styles.typeGreen}>{transaction.action}</div>
              </div>
            ))
          ) : (
            <div style={styles.title}>Start Investing, Make Profits!</div>
          )}
        </div>
      </div>
      <div style={styles.history}>
        <div style={styles.title}>Investor Updates</div>
        <div style={styles.orders}>
          {transactions.length > 0 ? (
            transactions.map((transaction, index) => (
              <div style={styles.order} key={index}>
                <div style={styles.info}>
                  <div key={index}>
                    <div style={styles.priceQuantity}>
                      {transaction.user} {transaction.type} {transaction.quantity} {transaction.stock_name}
                    </div>
                  </div>
                </div>
              </div>
            ))
          ) : (
            <div style={styles.title}>Market will be live soon, stay tuned!</div>
          )}
        </div>
      </div>
    </div>
    </main>
  );
}