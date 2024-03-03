import React, { useEffect} from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import * as styles from './styles/productStyle';
import { AppDispatch, RootState } from './redux/Store';
import { closeSnackbar, openSnackbar } from './redux/slices/productSlice';

export function ProductDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const dispatch: AppDispatch = useDispatch();
  const products = useSelector((state: RootState) => state.products.products);
  const product = products.find((product) => product.id === parseInt(id!));
  const snackbarOpen = useSelector((state: RootState) => state.products.snackbarOpen);

  useEffect(() => {
    dispatch(
      openSnackbar({
        message: `Product ${id} details!`,
        status: 'info',
      })
    );
  }, [dispatch, id]);

  useEffect(() => {
    if (snackbarOpen) {
      const timer = setTimeout(() => {
        dispatch(closeSnackbar());
      }, 6000);
      return () => clearTimeout(timer);
    }
  }, [snackbarOpen, dispatch]);

  const handleBack = () => {
    navigate(-1);
  };

  if (!product) {
    dispatch(
      openSnackbar({
        message: 'Product not found!',
        status: 'error',
      })
    );
    return <div>Product not found</div>;
  }

  return (
    <div style={styles.product}>
      <h2 style={styles.heading}>{product.title}</h2>
      <div style={styles.content}>
        <img src={product.image} alt={product.title} style={styles.image} />
        <div style={styles.details}>
          <p style={styles.price}>Price: ${product.price}</p>
          <p style={styles.category}>Category: {product.category}</p>
          <p style={styles.decription}>Description: {product.description}</p>
          <button style={styles.backBtn} onClick={handleBack}>
            Back to Products
          </button>
        </div>
      </div>
    </div>
  );
}
