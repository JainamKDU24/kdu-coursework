import styled, { css } from 'styled-components';

export const PortfolioContainer = styled.div`
  padding: 4rem 7rem;
  display: flex;
  justify-content: space-between;

  @media (max-width: 1700px) {
    padding: 4rem 4rem
  }

  @media (max-width: 780px) {
    align-items: center;
    flex-direction: column;
    padding: 3rem 1.5rem
  }
`;

export const TransactionsContainer = styled.div`
  font-size: 1.2rem;
  width: 100%;
  max-height: 70vh;
  overflow-y: scroll;
  scrollbar-width: none;
  -ms-overflow-style: none;

  @media (max-width: 850px) {
    max-height: 30vh
  }
`;

export const DateWiseContainer = styled.div`
  margin-bottom: 2rem;
`;

export const TransactionsDate = styled.div`
  color: #8b8b8c;
  padding: 1rem 0;
  border-bottom: 1px dotted #8b8b8c;
`;

export const Transactions = styled.div``;

interface selectedProp {
  filtered: boolean;
}
export const Transaction = styled.div<selectedProp>`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 2rem 0;
  border-bottom: 1px solid black;
  opacity: 0.7;
  color: grey;

  ${({ filtered }) =>
    filtered &&
    css`
      opacity: 1;
      color: inherit;
    `}
`;

export const Info = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 95%;
  flex-wrap: wrap;
`;

export const NameSymbol = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 450px;

  @media (max-width: 1700px) {
    width: 90%;
    margin-bottom: 1rem;
  }
`;

export const PriceTime = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 450px;

  @media (max-width: 1700px) {
    width: 90%
  }
`;

export const Outcome = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-end;
`;

export const Green = styled.svg`
  fill: #6bb97b;
`;

export const Red = styled.svg`
  fill: #e76b6b;
`;

export const HideScroll = styled.div`
  overflow-y: scroll;
  scrollbar-width: none;
  -ms-overflow-style: none;
`;

export const transactionFiltered: React.CSSProperties = {
  opacity: 1,
};

export const transactionLight: React.CSSProperties = {
  opacity: 0.7,
  color: "#c5c5c6",
};

export const FilterContainer = styled.div`
  border: 2px solid #1e1e1e;
  border-radius: 25px;
  display: flex;
  flex-direction: column;
  background-color: #e9ecef;
  width: 350px;
  color: #848687;
  margin-right: 6rem;
  height: fit-content;

  @media (max-width: 850px) {
    width: 300px;
    margin-right: 0;
    margin-bottom: 2rem;
  }

  @media (max-width: 400px) {
    width: 250px;
  }
`;

export const FilterHeader = styled.div`
  padding: 0.7rem 1rem;
  border-bottom: 2px solid #78797a;
`;

export const Header = styled.div`
  display: flex;
  justify-content: space-between;
  font-size: 1.2rem;
  color: #000;
`;

export const ClearAll = styled.button`
  color: #1971c2;
  cursor: pointer;
  border: none;
  background: transparent;
  font-size: 1.2rem;
  padding: 0;
`;

export const CheckboxContainer = styled.div`
  display: flex;
  align-items; center;
`;

export const StyledCheckbox = styled.input`
  cursor: pointer;
  margin-right: 10px;
`;

export const SearchBar = styled.div`
  padding: 0.3rem 1rem;
  display: flex;
  align-items: center;
  border: 1px solid #6f7072;
  border-bottom: 1px solid #6f7072;
  border-radius: 5px;
  font-size: 1rem;
`;

export const SearchInput = styled.input`
  width: 100%;
  background: transparent;
  border: none;
  font-size: 1rem;
  outline: none;
`;

export const DateContainer = styled.div`
  padding: 0.7rem 0.5rem;
  border-bottom: 2px solid #78797a;
  display: flex;
  justify-content: space-between;
`;

export const DateInput = styled.input`
  padding: 0.4rem 0;
  background: transparent;
  border: 1px solid #6f7072;
  border-radius: 5px;
  font-size: 1rem;
  color: #848687;
  margin: 0 0.5rem;
`;

export const TransactionOutcomeContainer = styled.div`
  padding: 0.7rem 1rem;
  display: flex;
  flex-direction: column;
  border-bottom: 2px solid #78797a;
  font-size: 1rem;
`;

export const StocksFilterContainer = styled.div`
  padding: 0.7rem 1rem;
  display: flex;
  flex-direction: column;
  font-size: 1rem;
  max-height: 20vh;
  overflow-y: scroll;
  scrollbar-width: none;
  -ms-overflow-style: none;
`;

export const FilterCheckbox = styled.input`
  cursor: pointer;
`;

export const FilterLabel = styled.label`
  cursor: pointer;
`;

export const FilterCheckboxContainer = styled.div`
  display: flex;
  align-items: center;
`;
