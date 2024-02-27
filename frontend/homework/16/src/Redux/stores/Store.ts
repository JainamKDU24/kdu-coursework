import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { Reducer } from '../slices/todoSlice';
import storageSession from 'redux-persist/lib/storage/session'
import { persistReducer } from "redux-persist";

const persistConfig = {
  key: 'root',
  storage: storageSession
};

const reducer = combineReducers({
  todo:Reducer,
});


const persistedReducer = persistReducer(persistConfig, reducer);

export const store = configureStore({
  reducer:persistedReducer,
});

export type RootState = ReturnType<typeof store.getState>;








