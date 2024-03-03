import React, { useEffect, useRef } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setFilter, setSearchTerm, } from './redux/slices/headerSlice';
import { ClipLoader } from 'react-spinners'; 
import { Header } from './Header';
import { Content } from './Content';
import { ProductDetailPage } from './productDetail';
import { AppDispatch, RootState } from './redux/Store';
import { getProducts } from './getProducts';
import { closeSnackbar } from './redux/slices/productSlice';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';

export function Marketplace() {
  const dispatch: AppDispatch = useDispatch();
  const searchInputRef = useRef<HTMLInputElement | null>(null);
  const isLoading = useSelector((state: RootState) => state.products.status);
  const snackbarOpen = useSelector((state: RootState) => state.products.snackbarOpen);
  const snackbarMessage = useSelector((state: RootState) => state.products.snackbarMessage);
  const snackbarStatus = useSelector((state: RootState) => state.products.snackbarStatus);

  useEffect(() => {
    dispatch(getProducts());
  }, [dispatch]);

  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const filterParam = urlParams.get('filter');
    dispatch(setFilter(filterParam || 'All'));
  }, [dispatch]);

  const handleSearch = () => {
    dispatch(setSearchTerm(searchInputRef.current?.value || ''));
  };

  const handleCloseSnackbar = () => {
    dispatch(closeSnackbar());
  };

  return (
    <div>
      <Router>
        <Header handleSearch={handleSearch} />
        {isLoading==='pending' ? (
          <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <ClipLoader color='#0C359E' loading={isLoading==='pending'} size={120} aria-label="Loading Spinner" />
          </div>
        ) : (
          <Routes>
            <Route path="/" element={<Content />} />
            <Route path="/product/:id" element={<ProductDetailPage />} />
          </Routes>
        )}
      </Router>
      {snackbarOpen && (
        <Snackbar open={snackbarOpen} autoHideDuration={1500} onClose={handleCloseSnackbar}>
          <Alert variant="filled" onClose={handleCloseSnackbar} severity={snackbarStatus}>
            {snackbarMessage}
          </Alert>
      </Snackbar>
      )}
    </div>
  );
}
