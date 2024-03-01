import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { getDetails } from '../thunks/getDetails'; 
interface AddOn {
  name: string;
  cost: number; 
  currency: string;
}

export interface RoomItem {
  id: number;
  name: string;
  costPerNight: number;
  currency: string;
  addOns: AddOn[];
}

interface RoomState {
  roomTypes: RoomItem[];
  searchTerm: string;
  loading: boolean;
  error: string | null;
}

const initialState: RoomState = {
  roomTypes: [],
  searchTerm: '',
  loading: false,
  error: null,
};

export const roomSlice = createSlice({
  name: 'rooms',
  initialState,
  reducers: {
    addRoom: (state, action: PayloadAction<RoomItem>) => {
      state.roomTypes.push(action.payload);
    },
    deleteRoom: (state, action: PayloadAction<number>) => {
      state.roomTypes = state.roomTypes.filter(room => room.id !== action.payload);
    },
    updateRoom: (state, action: PayloadAction<{ id: number; updates: Partial<RoomItem> }>) => {
      const { id, updates } = action.payload;
      const room = state.roomTypes.find(room => room.id === id);
      if (room) {
        Object.assign(room, updates);
      }
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(getDetails.pending, (state) => {
        state.loading = true;
      })
      .addCase(getDetails.fulfilled, (state, action) => {
        state.roomTypes = action.payload.roomTypes; 
        state.loading = false;
      })
      .addCase(getDetails.rejected, (state) => {
        state.error = 'failed to fetch details';
        state.loading = false;
      });
  },
});

export const { addRoom, deleteRoom, updateRoom } = roomSlice.actions;
export const RoomReducer = roomSlice.reducer;
