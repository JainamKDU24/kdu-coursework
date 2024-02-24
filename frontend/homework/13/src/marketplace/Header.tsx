import React, { useContext, useRef } from 'react';
import { CategoryContext, SortContext, SearchContext } from './Main';
import * as styles from './headerStyles';

export function Header({ handleSearch }) {
  const { setSearchTerm } = useContext(SearchContext);
  const searchInputRef = useRef(null);

  const { categories, selectedFilter, setSelectedFilter } = useContext(CategoryContext);
  const { selectedSort, setSelectedSort } = useContext(SortContext);

  const handleFilterChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedFilter(event.target.value);
  };

  const handleSortChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedSort(event.target.value);
  };

  const handleSearchButtonClick = () => {
    handleSearch();
    setSearchTerm(searchInputRef.current.value);
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
