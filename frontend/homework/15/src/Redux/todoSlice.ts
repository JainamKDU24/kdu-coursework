import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface TodoItem {
  id: number;
  content: string;
  completed: boolean; 
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
        completed: false,
      };
      state.itemsList.push(newItem);
    },
    deleteItem: (state, action: PayloadAction<number>) => {
      state.itemsList = state.itemsList.filter(item => item.id !== action.payload);
    },
    toggleItem: (state, action: PayloadAction<number>) => {
      const item = state.itemsList.find(item => item.id === action.payload);
      if (item) {
        item.completed = !item.completed;
      }
    },
    setSearchTerm: (state, action: PayloadAction<string>) => {
      state.searchTerm = action.payload;
    },
    clearCompleted: (state) => {
      state.itemsList = state.itemsList.filter(item => !item.completed);
    },
  },
});

export const { addItem, deleteItem, toggleItem, setSearchTerm, clearCompleted } = todoSlice.actions;
export const Reducer = todoSlice.reducer;
