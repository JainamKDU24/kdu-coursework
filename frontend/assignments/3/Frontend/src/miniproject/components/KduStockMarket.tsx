import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Header } from './Dashboard/Header';
import { Dashboard } from './Dashboard/Dashboard';
import { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { getStocks } from '../middleware/thunks/getStocks';
import { AppDispatch } from '../middleware/stores/Store';
import { Summarizer } from './Summarizer/Summarizer';
import { Portfolio } from './Portfolio/Portfolio';
import { StockDetail } from './StockInfo/StockDetail';
import { getTransactions } from '../middleware/thunks/getTransactions';
import io from 'socket.io-client';

const socket = io('http://localhost:5000');

export function KduStockMarket() {
  const dispatch: AppDispatch = useDispatch();
  const [userName, setUserName] = useState("");

  useEffect(() => {
    dispatch(getStocks());
    dispatch(getTransactions());

    // Fetch username from the server
    socket.on('userName', (name) => {
      setUserName(name);
    });
    console.log(userName);
    return () => {
      socket.off('userName');
    };
  }, [dispatch]);

  return (
    <div>
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/summariser" element={<Summarizer />} />
          <Route path="/portfolio" element={<Portfolio />} />
          <Route
            path="/stock/:stock_name"
            element={<StockDetail Name={userName} />}
          />
        </Routes>
      </Router>
    </div>
  );
}
