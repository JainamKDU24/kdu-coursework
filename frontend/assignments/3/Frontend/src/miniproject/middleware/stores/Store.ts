import { configureStore } from "@reduxjs/toolkit";
import stocksReducer from "../slices/stockSlice";
import transactionsReducer from "../slices/transactionSlice";

export const store = configureStore({
  reducer: {
    stocks: stocksReducer,
    transactions: transactionsReducer
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

