import React from 'react';
import './componentStyles/Header.scss';
import { useDispatch } from 'react-redux';
import { setSearchTerm } from '../Redux/slices/todoSlice';

export function Header() {
  const headerDispatch = useDispatch();

  const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    headerDispatch(setSearchTerm(e.target.value));
  };

  return (
    <header>
      <div className="Header">
        <div className="title">
          <h1>Item Lister</h1>
        </div>
        <div className="search-bar">
          <input
            type="search"
            name="search"
            id="search"
            placeholder="Search Item..."
            onChange={handleSearch}
          />
        </div>
      </div>
    </header>
  );
}
