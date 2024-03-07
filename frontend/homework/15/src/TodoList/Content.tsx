// Content.tsx
import React, { useState } from 'react';
import "./Content.scss"
import { useDispatch, useSelector } from 'react-redux';
import { addItem, deleteItem, toggleItem, clearCompleted } from '../Redux/todoSlice';
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

  const handleDeleteItem = (id: number) => {
    contentDispatch(deleteItem(id));
  };

  const handleToggleItem = (id: number) => {
    contentDispatch(toggleItem(id));
  };

  const handleClearCompleted = () => {
    contentDispatch(clearCompleted());
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
            <>
              <ul>
                {filteredItems.map(item => (
                  <li key={item.id} className={`items ${item.completed ? 'completed' : ''}`}>
                    <div>
                      <input
                        type="checkbox"
                        checked={item.completed}
                        onChange={() => handleToggleItem(item.id)}
                      />
                      <span className={item.completed ? 'completed-text' : ''}>{item.content}</span>
                    </div>
                    <button
                      className="deleteButton"
                      onClick={() => handleDeleteItem(item.id)}
                    >
                      X
                    </button>
                  </li>
                ))}
              </ul>
            </>
          )}
          <button className="clearButton" onClick={handleClearCompleted}>
            Clear Completed
          </button>
        </div>
      </div>
    </div>
  );
}
