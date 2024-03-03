import React, { useState } from 'react';
import './componentStyles/Content.scss';
import { useDispatch, useSelector } from 'react-redux';
import { addItem, deleteItem, toggleItem, clearCompleted } from '../Redux/slices/todoSlice';
import { RootState } from '../Redux/stores/Store';

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
    <div className="todo-list" data-testid="todo-list">
      <div className="input" data-testid="input">
        <h2>Add Items</h2>
        <input
          type="text"
          name="item"
          id="item"
          value={inputValue}
          onChange={e => setInputValue(e.target.value)}
          data-testid="input-text"
        />
        <button className="submit" onClick={handleAddItem} disabled={!inputValue.trim()} data-testid="submit-button">
          Submit
        </button>
      </div>
      <div className="list" data-testid="list">
        <h2>Items</h2>
        <div className="elements" data-testid="elements">
          {noItemsAdded && <p data-testid="no-items">No items added</p>}
          {noSearchedItemsFound && <p data-testid="no-match">No match found</p>}
          {!noItemsAdded && !noSearchedItemsFound && (
            <>
              <ul data-testid="item-list">
                {filteredItems.map(item => (
                  <li key={item.id} className={`items ${item.completed ? 'completed' : ''}`} data-testid={`item-${item.id}`}>
                    <div>
                      <input
                        type="checkbox"
                        checked={item.completed}
                        onChange={() => handleToggleItem(item.id)}
                        data-testid={`item-checkbox-${item.id}`}
                      />
                      <span className={item.completed ? 'completed-text' : ''} data-testid={`item-content-${item.id}`}>{item.content}</span>
                    </div>
                    <button
                      className="deleteButton"
                      onClick={() => handleDeleteItem(item.id)}
                      data-testid={`delete-button-${item.id}`}
                    >
                      X
                    </button>
                  </li>
                ))}
              </ul>
            </>
          )}
          <button className="clearButton" onClick={handleClearCompleted} data-testid="clear-button">
            Clear Completed
          </button>
        </div>
      </div>
    </div>
  );
}
