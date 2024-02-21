import React, { useEffect, useState } from 'react';
import { APIQuote } from './types/quotes.types';
import { Quote } from './QuotesList/Quote';
import { Header } from './QuotesList/Header';
import './App.scss';

function App() {
  const [quotes, setQuotes] = useState<APIQuote[]>([]);
  const [selectedTags, setSelectedTags] = useState<string[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  useEffect(() => {
    fetch('https://api.quotable.io/quotes/random?limit=3')
      .then(response => response.json())
      .then((data: APIQuote[]) => {
        setQuotes(data);
      });
  }, []);

  const handleTagClick = (tag: string) => {
    if (!selectedTags.includes(tag)) {
      setSelectedTags([...selectedTags, tag]);
    }
  };

  const handleTagCancel = (tag: string) => {
    const updatedTags = selectedTags.filter(selectedTag => selectedTag !== tag);
    setSelectedTags(updatedTags);
  };

  const fetchNewQuote = () => {
    setIsLoading(true);
    fetch('https://api.quotable.io/quotes/random?limit=1')
      .then(response => response.json())
      .then((data: APIQuote[]) => {
        setQuotes([...data, ...quotes]);
        setIsLoading(false);
      })
      .catch(error => {
        console.error('Error fetching new quote:', error);
        setIsLoading(false);
      });
  };

  const filteredQuotes = selectedTags.length > 0
    ? quotes.filter(quote => quote.tags.some(tag => selectedTags.includes(tag)))
    : quotes;

  return (
    <>
      <Header fetchNewQuote={fetchNewQuote} selectedTags={selectedTags} onTagCancel={handleTagCancel} isLoading={isLoading} />
      {filteredQuotes.map(quote => (
        <Quote key={quote._id} quote={quote} onTagClick={handleTagClick} />
      ))}
    </>
  );
}

export default App;
