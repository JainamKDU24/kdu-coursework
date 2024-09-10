import React, { useState } from 'react';
import "./Content.scss"

interface IItem {
  id: number;
  content: string;
}

interface ItemsProp {
  itemsList: IItem[];
  setItemsList: React.Dispatch<React.SetStateAction<IItem[]>>;
  searchTerm: string;
}

export function Content({ itemsList, setItemsList, searchTerm }: ItemsProp) {
  const [inputValue, setInputValue] = useState<string>('');

  const addItem = () => {
    if (inputValue.trim() !== '') {
      const newItem: IItem = {
        id: itemsList.length + 1,
        content: inputValue.trim(),
      };

      setItemsList(prevItems => [...prevItems, newItem]);
      setInputValue('');
    }
  };

  const filteredItems = itemsList.filter(
    item => item.content.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const noItemsAdded = itemsList.length === 0 && searchTerm === '';
  const noSearchedItemsFound = filteredItems.length === 0 && searchTerm !== '';


  return (
    <div className="todo-list">
      <div className="input">
        <h2>Add Items</h2>
        <input
          type="text"
          name="item"
          id="item"
          value={inputValue}
          onChange={e => setInputValue(e.target.value)}
        />
        <button className="submit" onClick={addItem}>
          Submit
        </button>
      </div>
      <div className="list">
        <h2>Items</h2>
        <div className="elements">
          {noItemsAdded && <p >No items added</p>}
          {noSearchedItemsFound && <p>No match found</p>}
          {!noItemsAdded && !noSearchedItemsFound && (
            <ul>
              {filteredItems.map(item => (
                <li key={item.id} className="items">
                  {item.content}
                  <button
                    className="deleteButton"
                    onClick={() =>
                      setItemsList(prevItems =>
                        prevItems.filter(prevItem => prevItem.id !== item.id)
                      )
                    }
                  >
                    X
                  </button>
                </li>
              ))}
            </ul>
          )}
        </div>
      </div>
    </div>
  );
}
