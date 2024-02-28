import { createSlice } from '@reduxjs/toolkit';

interface searchType{
  searchTerm:string | undefined ,
  filter: string,
  sort: string,
}
const initialState: searchType = {
  searchTerm: "",
  filter: 'All',
  sort: 'none',
};

const headerSlice = createSlice({
  name: 'header',
  initialState,
  reducers: {
    setFilter(state, action) {
      state.filter = action.payload;
    },
    setSort(state, action) {
      state.sort = action.payload;
    },
    setSearchTerm(state, action) {
      state.searchTerm = action.payload;
    },
  },
});

export const { setFilter, setSort, setSearchTerm } = headerSlice.actions;
export default headerSlice.reducer;