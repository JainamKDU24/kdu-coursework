import React, { createContext, useContext, useState } from 'react';

interface TodoItem {
  id: number;
  content: string;
}

interface TodoContextType {
  itemsList: TodoItem[];
  addItem: (content: string) => void;
  deleteItem: (id: number) => void;
  searchTerm: string;
  setSearchTerm: (term: string) => void;
  setItemsList: React.Dispatch<React.SetStateAction<TodoItem[]>>; 
}

export const TodoContext = createContext<TodoContextType>({
  itemsList: [],
  addItem: () => {},
  deleteItem: () => {},
  searchTerm: '',
  setSearchTerm: () => {},
  setItemsList: () => {}
});



interface TodoProviderProps {
    children: React.ReactNode;
}

export function TodoProvider({ children }: TodoProviderProps) {
    const [itemsList, setItemsList] = useState<TodoItem[]>([]);
    const [searchTerm, setSearchTerm] = useState<string>('');
  
    const addItem = (content: string) => {
      if (content.trim() !== '') {
        const newItem: TodoItem = {
          id: itemsList.length + 1,
          content: content.trim(),
        };
        setItemsList(prevItems => [...prevItems, newItem]);
      }
    };
  
    const deleteItem = (id: number) => {
      setItemsList(prevItems => prevItems.filter(item => item.id !== id));
    };
  
    return (
      <TodoContext.Provider value={{ itemsList, addItem, deleteItem, searchTerm, setSearchTerm,setItemsList }}>
        {children}
      </TodoContext.Provider>
    );
  }

