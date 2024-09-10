import React from 'react';
import './Quote.scss';
import { APIQuote } from '../types/quotes.types';

interface QuoteProps {
  quote: APIQuote;
  onTagClick: (tag: string) => void;
}

export function Quote({ quote, onTagClick }: QuoteProps) {

  console.log(quote);

  return (
    <div className="content">
      <div className="quote-item">
        <h1>{quote.content}</h1>
        <p className="author">~{quote.author}</p>
        <p className="dateAdded">{quote.dateAdded}</p>
        <div className="tags">
        {quote.tags?.map((tag) => (
          <button key={tag} className="tag-btn" onClick={() => onTagClick(tag)}>
            <span>{tag}</span>
          </button>
        ))}
        </div>
      </div>
    </div>
  );
}
