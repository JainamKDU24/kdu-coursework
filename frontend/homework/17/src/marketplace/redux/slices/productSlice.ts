import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { getProducts } from '../../getProducts';

interface Product {
  id: number;
  title: string;
  category: string;
  price: number;
  image: string;
  description: string;
}

interface ProductsState {
  products: Product[];
  categories: string[];
  status: 'pending' | 'fulfilled' | 'rejected';
  error: string;
  snackbarOpen: boolean;
  snackbarMessage: string;
  snackbarStatus: 'success' | 'error' | 'info';
}

const initialState: ProductsState = {
  products: [],
  categories: [],
  status: 'pending',
  error: '',
  snackbarOpen: false,
  snackbarMessage: '',
  snackbarStatus: 'success',
};

const productsSlice = createSlice({
  name: 'products',
  initialState,
  reducers: {
    setProducts(state, action: PayloadAction<Product[]>) {
      state.products = action.payload;
      state.categories = ['All', ...new Set(action.payload.map(product => product.category))];
      state.status = 'fulfilled';
    },
    openSnackbar(state, action: PayloadAction<{ message: string; status: 'success' | 'error' |'info' }>) {
      state.snackbarOpen = true;
      state.snackbarMessage = action.payload.message;
      state.snackbarStatus = action.payload.status;
    },
    closeSnackbar(state) {
      state.snackbarOpen = false;
    },
  },
  extraReducers(builder) {
    builder
      .addCase(getProducts.pending, state => {
        state.status = 'pending';
      })
      .addCase(getProducts.fulfilled, (state, action) => {
        state.products = action.payload.data;
        state.categories = action.payload.categories as string[];
        state.status = 'fulfilled';
        state.snackbarOpen = true;
        state.snackbarMessage = 'Products fetched successfully!';
        state.snackbarStatus = 'success';
      })
      .addCase(getProducts.rejected, (state, action) => {
        state.status = 'rejected';
        state.error = action.payload as string;
        state.snackbarOpen = true;
        state.snackbarMessage = 'Error fetching products!';
        state.snackbarStatus = 'error';
      });
  },
});

export const { setProducts, openSnackbar, closeSnackbar } = productsSlice.actions;

export default productsSlice.reducer;
