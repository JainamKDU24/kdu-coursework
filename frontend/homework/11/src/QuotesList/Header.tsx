import './Header.scss';
import ClipLoader from "react-spinners/ClipLoader";


interface HeaderProps {
  fetchNewQuote: () => void;
  selectedTags: string[];
  onTagCancel: (tag: string) => void;
  isLoading: boolean;
}

export function Header({ fetchNewQuote, selectedTags, onTagCancel, isLoading }: HeaderProps) {

  const handleTagCancel = (tag: string) => {
    onTagCancel(tag);
  };

  return (
    <header>
      <div className="Header">
        <div className="title">
        <button className="fetch-quote" onClick={fetchNewQuote} disabled={isLoading}>
            {isLoading ? (
              <>
              <span>NEW QUOTE</span>
              <ClipLoader color='#0C359E' loading={isLoading} size={30} aria-label="Loading Spinner" />
              </>
            ) : (
              <span>NEW QUOTE</span>
            )}
          </button>
        </div>
        <div className="filter">
          <p>Filters</p>
          {selectedTags.map(tag => (
            <button key={tag} className="tag">
              {tag} <button className='cancel' onClick={() => handleTagCancel(tag)}> X </button>
            </button>
          ))}
        </div>
      </div>
    </header>
  );
}
