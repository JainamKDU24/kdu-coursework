import { createSlice } from "@reduxjs/toolkit";
import { getStocks } from "../thunks/getStocks";

export interface Stock {
  stock_name: string;
  stock_symbol: string;
  base_price: number;
}

interface StocksState {
  data: Stock[];
  watchlist: string[]; 
  status: "loading" | "succeeded" | "failed";
  error: string | null;
  snackbarOpen: boolean;
  snackbarMessage: string;
  snackbarStatus: "success" | "error" | undefined;
}

const initialState: StocksState = {
  data: [],
  watchlist: [], 
  status: "loading",
  error: null,
  snackbarOpen: false,
  snackbarMessage: "",
  snackbarStatus: undefined,
};

const stocksSlice = createSlice({
  name: "stocks",
  initialState,
  reducers: {
    addToWatchlist(state, action) {
      const stockName = action.payload;
      if (!state.watchlist.includes(stockName)) {
        state.watchlist.push(stockName);
      }
    },
    removeFromWatchlist(state, action) {
      const stockName = action.payload;
      state.watchlist = state.watchlist.filter((name) => name !== stockName);
    },
    openSnackbar(state, action) {
      state.snackbarOpen = true;
      state.snackbarMessage = action.payload.message;
      state.snackbarStatus = action.payload.status;
    },
    closeSnackbar(state) {
      state.snackbarOpen = false;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(getStocks.pending, (state) => {
        state.status = "loading";
      })
      .addCase(getStocks.fulfilled, (state, action) => {
        state.status = "succeeded";
        state.data = action.payload;
      })
      .addCase(getStocks.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message ?? "Failed to fetch stocks data";
      });
  },
});

export const { addToWatchlist, removeFromWatchlist, openSnackbar, closeSnackbar } = stocksSlice.actions;

export default stocksSlice.reducer;
