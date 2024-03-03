import { useState, useEffect } from 'react'; 
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../../middleware/stores/Store';
import * as styles from '../../styles/dashboardStyles';
import { addToWatchlist, removeFromWatchlist, openSnackbar, closeSnackbar } from '../../middleware/slices/stockSlice'; // Import the Snackbar-related actions
import Pagination from '@mui/material/Pagination';
import { ClipLoader } from 'react-spinners';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';

const AddIcon = () => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="#468ccd">
    <path d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm6 13h-5v5h-2v-5h-5v-2h5v-5h2v5h5v2z" />
  </svg>
);

const TickIcon = () => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"  fill="#468ccd">
    <path d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-1 17l-5-5.299 1.399-1.43 3.574 3.736 6.572-7.007 1.455 1.403-8 8.597z" />
  </svg>
);

const RemoveIcon = () => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="#e55959">
    <path d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm4.151 17.943l-4.143-4.102-4.117 4.159-1.833-1.833 4.104-4.157-4.162-4.119 1.833-1.833 4.155 4.102 4.106-4.16 1.849 1.849-4.1 4.141 4.157 4.104-1.849 1.849z" />
  </svg>
);

export function Dashboard() {
  const [activeTab, setActiveTab] = useState('Explore');
  const [hoveredStock, setHoveredStock] = useState<string | null>(null); 
  const status = useSelector((state: RootState) => state.stocks.status);
  const [page, setPage] = useState(1); 
  const stocksPerPage = 7; 
  const dispatch = useDispatch();

  useEffect(() => {
    setPage(1); 
  }, [activeTab]); 

  const handleTabClick = (tabName: string) => {
    setActiveTab(tabName);
  };

  const stocks = useSelector((state: RootState) => state.stocks.data);
  const watchlist = useSelector((state: RootState) => state.stocks.watchlist);

  const displayedStocks = activeTab === 'My WatchList' ? stocks.filter((stock) => watchlist.includes(stock.stock_name)) : stocks;

  const sortedStocks = [...displayedStocks].sort((a, b) => a.stock_name.localeCompare(b.stock_name));

  const startIndex = (page - 1) * stocksPerPage;
  const endIndex = startIndex + stocksPerPage;
  const slicedStocks = sortedStocks.slice(startIndex, endIndex);

  const handlePageChange = (event: React.ChangeEvent<unknown>, value: number) => {
    setPage(value);
  };

  const handleAddToWatchlist = (stockName: string) => {
    dispatch(addToWatchlist(stockName));
    dispatch(openSnackbar({ message: "Added to Watchlist", status: "success" }));
  };

  const handleRemoveFromWatchlist = (stockName: string) => {
    dispatch(removeFromWatchlist(stockName));
    dispatch(openSnackbar({ message: "Removed from Watchlist", status: "success" }));
  };

  const handleMouseEnter = (stockName: string) => {
    setHoveredStock(stockName);
  };

  const handleMouseLeave = () => {
    setHoveredStock(null);
  };

  const snackbarOpen = useSelector((state: RootState) => state.stocks.snackbarOpen);
  const snackbarMessage = useSelector((state: RootState) => state.stocks.snackbarMessage);
  const snackbarStatus = useSelector((state: RootState) => state.stocks.snackbarStatus);

  const handleCloseSnackbar = () => {
    dispatch(closeSnackbar());
  };

  useEffect(() => {
    if (status === 'succeeded') {
      dispatch(openSnackbar({ message: "Data loaded successfully", status: "success" }));
    }
  }, [status, dispatch]);

  return (
    <styles.Dashboard>
      {status === 'loading' ? (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
          <ClipLoader color='#0C359E' loading={true} size={120} aria-label="Loading Spinner" />
        </div>
      ) : (
        <>
          <styles.Elements>
            <styles.DashboardItem onClick={() => handleTabClick("Explore")}>
              Explore
              <styles.BorderBottom style={activeTab === "Explore" ? {} : { visibility: 'hidden' }} />
            </styles.DashboardItem>
            <styles.DashboardItem onClick={() => handleTabClick("My WatchList")}>
              My WatchList
              <styles.BorderBottom style={activeTab === "My WatchList" ? {} : { visibility: 'hidden' }} />
            </styles.DashboardItem>
          </styles.Elements>
          <styles.StockListing>
            <styles.ListingTitle>
              <div>Company</div>
              <styles.PriceWatch>
                <styles.PriceTitle>Base Price</styles.PriceTitle>
                <div>Watchlist</div>
              </styles.PriceWatch>
            </styles.ListingTitle>
            {
              slicedStocks.length === 0 && activeTab === 'My WatchList' ? (
                <styles.NoStocks>
                  No stocks added to Watchlist, Start Exploring!
                </styles.NoStocks>
              ) : (
                slicedStocks.map((stock, index) => (
                  <styles.Stock key={index}>
                    <styles.LinkStyled to={`/stock/${stock.stock_name}`}>
                      <styles.StockName>{stock.stock_name}</styles.StockName>
                    </styles.LinkStyled>
                    <styles.PriceWatch>
                      <styles.Price>{stock.base_price}</styles.Price>
                      <styles.WatchlistIcon
                        onMouseEnter={() => handleMouseEnter(stock.stock_name)}
                        onMouseLeave={handleMouseLeave}
                        onClick={() => watchlist.includes(stock.stock_name) ? handleRemoveFromWatchlist(stock.stock_name) : handleAddToWatchlist(stock.stock_name)}
                      >
                        {watchlist.includes(stock.stock_name) ? (
                          hoveredStock === stock.stock_name ? (
                            <RemoveIcon />
                          ) : (
                            <TickIcon />
                          )
                        ) : (
                          <AddIcon />
                        )}
                      </styles.WatchlistIcon>
                    </styles.PriceWatch>
                  </styles.Stock>
                ))
              )
            }
            <styles.Pagination>
              <Pagination count={Math.ceil(displayedStocks.length / stocksPerPage)} color="primary" page={page} onChange={handlePageChange} />
            </styles.Pagination>
          </styles.StockListing>
        </>
      )}
      <Snackbar open={snackbarOpen} autoHideDuration={1500} onClose={handleCloseSnackbar}>
        <Alert variant="filled" onClose={handleCloseSnackbar} severity={snackbarStatus}>
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </styles.Dashboard>
  );
}
