// Content.tsx
import React, { useContext } from 'react';
import { ProductContext, CategoryContext, SortContext, SearchContext } from './Main';
import * as styles from './contentStyles';
import { Link } from 'react-router-dom';

interface Product {
  id: number;
  title: string;
  category: string;
  price: number;
  image: string;
}

export function Content() {
  const products = useContext(ProductContext);
  const { selectedFilter } = useContext(CategoryContext);
  const { selectedSort } = useContext(SortContext);
  const { searchTerm } = useContext(SearchContext); 

  let filteredProducts = selectedFilter === 'All' ? products : products.filter(product => product.category === selectedFilter);

  if (selectedSort === 'ascending') {
    filteredProducts = [...filteredProducts].sort((a, b) => a.price - b.price);
  } else if (selectedSort === 'descending') {
    filteredProducts = [...filteredProducts].sort((a, b) => b.price - a.price);
  }

  if (searchTerm !== '') {
    filteredProducts = filteredProducts.filter(product =>
      product.title.toLowerCase().includes(searchTerm.toLowerCase())
    );
  }

  return (
    <main style={styles.main}>
      <div style={styles.contentContainer}>
        <div style={styles.title}>
          <span>KDU &nbsp; <span style={styles.colorTitle}>MARKETPLACE</span></span>
        </div>
        <div style={styles.productsContainer}>
          {filteredProducts.length > 0 ? (
            filteredProducts.map((product: Product) => (
              <div key={product.id} style={styles.productStyle}>
                <div style={styles.imageContainerStyle}>
                    <Link key={product.id} to={`/product/${product.id}`} style={{ textDecoration: 'none' }}>
                        <img style={styles.imageStyle} src={product.image} alt="product img" />
                    </Link>
                </div>
                <div style={styles.productInfoStyle}>
                  <div style={styles.sameline}>
                    <div style={styles.titleStyle}>{product.title}</div>
                    <div style={styles.priceStyle}>${product.price}</div>
                  </div>
                  <div style={styles.category}>{product.category}</div>
                </div>
              </div>
            ))
          ) : (
            <div style={styles.productNotFound}>Product Not Found</div>
          )}
        </div>
      </div>
    </main>
  );
}
