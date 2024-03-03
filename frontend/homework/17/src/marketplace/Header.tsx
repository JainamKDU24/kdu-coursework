import React, { useRef } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setFilter, setSort, setSearchTerm } from './redux/slices/headerSlice';
import * as styles from './styles/headerStyles';
import { AppDispatch, RootState } from './redux/Store';
interface HeaderProps {
  handleSearch: () => void; 
}

export function Header({ handleSearch }:HeaderProps) {
  const dispatch:AppDispatch = useDispatch();
  const categories = useSelector((state:RootState) => state.products.categories);
  const selectedFilter = useSelector((state:RootState) => state.header.filter);
  const selectedSort = useSelector((state:RootState) => state.header.sort);


  const searchInputRef = useRef<HTMLInputElement>(null);

  const handleFilterChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    dispatch(setFilter(event.target.value));
  };

  const handleSortChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    dispatch(setSort(event.target.value));
  };

  const handleSearchButtonClick = () => {
    handleSearch();
    dispatch(setSearchTerm(searchInputRef.current!.value));
  };

  return (
    <header>
      <div style={styles.headerStyle}>
        <div style={styles.searchbarStyle}>
          <input
            type="search"
            name="search"
            id="search"
            placeholder="Search Product..."
            ref={searchInputRef}
          />
          <button onClick={handleSearchButtonClick}>Search</button>
        </div>
        <div style={styles.optionsStyle}>
          <div style={styles.filterStyle}>
            Filter:
            <select onChange={handleFilterChange} value={selectedFilter}>
              {categories.map((category, index) => (
                <option key={index} value={category}>
                  {category}
                </option>
              ))}
            </select>
          </div>
          <div style={styles.sortStyle}>
            Sort:
            <select onChange={handleSortChange} value={selectedSort}>
              <option value="none">None</option>
              <option value="ascending">Ascending</option>
              <option value="descending">Descending</option>
            </select>
          </div>
        </div>
      </div>
    </header>
  );
}
