import React from 'react';
import "./Header.scss"
interface HeaderProps {
  setSearchTerm: React.Dispatch<React.SetStateAction<string>>;
}

export function Header({ setSearchTerm }: HeaderProps) {
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
