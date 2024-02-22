import React, { useContext } from 'react';
import './Header.scss';
import { TodoContext } from './TodoContext';

export function Header() {
  const { setSearchTerm } = useContext(TodoContext);

  const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
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
