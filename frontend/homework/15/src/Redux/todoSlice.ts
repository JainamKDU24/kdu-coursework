import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface TodoItem {
  id: number;
  content: string;
}

interface TodoState {
  itemsList: TodoItem[];
  searchTerm: string;
}

const initialState: TodoState = {
  itemsList: [],
  searchTerm: '',
};

export const todoSlice = createSlice({
  name: 'todo',
  initialState,
  reducers: {
    addItem: (state, action: PayloadAction<string>) => {
        const newItem: TodoItem = {
          id: state.itemsList.length + 1, 
          content: action.payload,
        };
        state.itemsList.push(newItem);
    },
    deleteItem: (state, action: PayloadAction<number>) => {
      state.itemsList = state.itemsList.filter(item => item.id !== action.payload);
    },
    setSearchTerm: (state, action: PayloadAction<string>) => {
      state.searchTerm = action.payload;
    },
  },
});

export const { addItem, deleteItem, setSearchTerm } = todoSlice.actions;
export const Reducer = todoSlice.reducer;
