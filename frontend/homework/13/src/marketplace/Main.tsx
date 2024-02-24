import React, { useState, useEffect, createContext, useRef } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Header } from './Header';
import { Content } from './Content';
import { ProductDetailPage } from './productDetail';

interface Product {
  id: number;
  title: string;
  category: string;
  price: number;
  image: string;
  description: string;
}

interface CategoryContextType {
  categories: string[];
  selectedFilter: string;
  setSelectedFilter: React.Dispatch<React.SetStateAction<string>>;
}

interface SortContextType {
  selectedSort: string;
  setSelectedSort: React.Dispatch<React.SetStateAction<string>>;
}

interface SearchContextType {
  searchTerm: string;
  setSearchTerm: React.Dispatch<React.SetStateAction<string>>;
}

export const ProductContext = createContext<Product[]>([]);
export const CategoryContext = createContext<CategoryContextType>({
  categories: [],
  selectedFilter: 'All',
  setSelectedFilter: () => {},
});

export const SortContext = createContext<SortContextType>({
  selectedSort: 'none',
  setSelectedSort: () => {},
});

export const SearchContext = createContext<SearchContextType>({
  searchTerm: '',
  setSearchTerm: () => {},
});

export function Main() {
  const [products, setProducts] = useState<Product[]>([]);
  const [categories, setCategories] = useState<string[]>([]);
  const [selectedFilter, setSelectedFilter] = useState('All');
  const [selectedSort, setSelectedSort] = useState('none');
  const [searchTerm, setSearchTerm] = useState('');

  const searchInputRef = useRef<HTMLInputElement | null>(null);
  useEffect(() => {
    fetch('https://fakestoreapi.com/products')
      .then(response => response.json())
      .then((data: Product[]) => {
        setProducts(data);
        const uniqueCategories = ['All', ...Array.from(new Set(data.map(product => product.category)))];
        setCategories(uniqueCategories);
      })
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const filterParam = urlParams.get('filter');
    console.log(filterParam);
    setSelectedFilter(filterParam || 'All');
  }, []);

  const handleSearch = () => {
    setSearchTerm(searchInputRef.current?.value || '');
  };

  return (
    <div>
      <Router>
        <ProductContext.Provider value={products}>
          <CategoryContext.Provider value={{ categories, selectedFilter, setSelectedFilter }}>
            <SortContext.Provider value={{ selectedSort, setSelectedSort }}>
              <SearchContext.Provider value={{ searchTerm, setSearchTerm }}>
                <Header handleSearch={handleSearch} /> 
                <Routes>
                  <Route path="/" element={<Content />} />
                  <Route path="/product/:id" element={<ProductDetailPage />} />
                </Routes>
              </SearchContext.Provider>
            </SortContext.Provider>
          </CategoryContext.Provider>
        </ProductContext.Provider>
      </Router>
    </div>
  );
}
