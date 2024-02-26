import React, { useState } from 'react';
import "./Content.scss"
import { useDispatch, useSelector } from 'react-redux';
import { addItem, deleteItem } from '../Redux/todoSlice';
import { RootState } from '../Redux/Store';

export function Content() {
  const contentDispatch = useDispatch();
  const itemsList = useSelector((state: RootState) => state.todo.itemsList);
  const searchTerm = useSelector((state: RootState) => state.todo.searchTerm);

  const [inputValue, setInputValue] = useState<string>('');

  const handleAddItem = () => {
    if (inputValue.trim() !== '') {
      contentDispatch(addItem(inputValue.trim()));
      setInputValue('');
    }
  };

  const filteredItems = itemsList.filter(
    item => item.content.toLowerCase().startsWith(searchTerm.toLowerCase())
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
        <button className="submit" onClick={handleAddItem}>
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
                      contentDispatch(deleteItem(item.id))
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
