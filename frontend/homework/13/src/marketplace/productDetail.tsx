import React, { useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom'; 
import { ProductContext } from './Main';
import * as styles from './productStyle';

export function ProductDetailPage() {
  const { id } = useParams(); 
  const navigate = useNavigate(); 
  const products = useContext(ProductContext); 
  const product = products.find(product => product.id === parseInt(id!)); 

  const handleBack = () => {
    navigate(-1); 
  };

  if (!product) {
    return <div>Product not found</div>;
  }

  return (
    <div style={styles.product}>
      <h2 style={styles.heading}>{product.title}</h2>
      <div style={styles.content}>
        <img src={product.image} alt={product.title} style={styles.image}/>
        <div style={styles.details}>
            <p style={styles.price}>Price: ${product.price}</p>
            <p style={styles.category}>Category: {product.category}</p>
            <p style={styles.decription}>Description: {product.description}</p>
            <button style={styles.backBtn} onClick={handleBack}>Back to Products</button>
        </div>
      </div>
    </div>
  );
}
