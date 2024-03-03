import { configureStore } from '@reduxjs/toolkit';
import productsReducer from './slices/productSlice';
import headerReducer from './slices/headerSlice';

export const store = configureStore({
  reducer: {
    products: productsReducer,
    header: headerReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;  
export type AppDispatch= typeof store.dispatch;