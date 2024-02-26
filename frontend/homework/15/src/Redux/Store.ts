import { configureStore } from "@reduxjs/toolkit";
import { Reducer } from './todoSlice';

export const store = configureStore({
  reducer: {
    todo: Reducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;