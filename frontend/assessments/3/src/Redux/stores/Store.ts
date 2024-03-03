import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { RoomReducer } from '../slices/roomSlice';
import storageSession from 'redux-persist/lib/storage/session'
import { persistReducer } from "redux-persist";

const persistConfig = {
  key: 'root',
  storage: storageSession
};

const reducer = combineReducers({
  hotel:RoomReducer,
});


const persistedReducer = persistReducer(persistConfig, reducer);

export const store = configureStore({
  reducer:persistedReducer,
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;









