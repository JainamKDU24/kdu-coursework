import { Link } from "react-router-dom";
import styled from "styled-components";

export const Dashboard = styled.div`
  padding: 0 1.25rem;
`;

export const Elements = styled.div`
  padding: 1.25rem 0;
  display: flex;
  align-items: center;
`;

export const BorderBottom = styled.div`
  border: 2px solid #468ccd;
  border-radius: 5px;
`;

export const StockListing = styled.div`
  width: 75%;
  font-size: 1.2rem;
  margin: 1.25rem auto;
  border: 4px solid #5c6065;
  border-radius: 25px;

  @media (max-width: 700px) {
    width: 95%;
  }
`;

export const ListingTitle = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 4px solid #5c6065;
  padding: 1.25rem 3rem;

  @media (max-width: 700px) {
    padding: 1.25rem 1rem;
  }
`;

export const PriceWatch = styled.div`
  width: 13rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export const Price = styled.div`
  margin-left: 0.7rem;
`;


export const PriceTitle = styled.div`
  margin-right: 0.7rem;
`;

export const Stock = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 0 3rem;
  padding: 1.25rem 0;
  border-bottom: 2px solid #d7d7d8;

  @media (max-width: 900px) {
    margin: 0 1rem;
  }
`;

export const StockName = styled.div`
  @media (max-width: 425px) {
    max-width: 100px
  }
`;

export const Pagination = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 1.25rem;

  @media (max-width: 900px) {
    padding: 1rem 0.5rem;
  }
`;

export const NoStocks = styled.div`
  text-align: center;
  padding-top: 1.25rem;
  font-size: 2rem;
`;

export const DashboardItem = styled.div`
  border: none;
  background: transparent;
  font-size: 1.3rem;
  cursor: pointer;
  margin-right: 1rem;
`;

export const WatchlistIcon = styled.div`
  display: flex;
  align-items: center;
  margin-right: 2rem;
  border: none;
  background: transparent;
  cursor: pointer;
`;

export const LinkStyled = styled(Link)`
  text-decoration: none;
  color: inherit;
`;

