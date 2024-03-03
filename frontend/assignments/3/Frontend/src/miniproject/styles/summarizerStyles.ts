import styled from 'styled-components';

export const SummaryContainer = styled.div`
  width: 85%;
  margin: auto;
  padding-top: 2%;
`;

export const Items = styled.div`
  background-color: #1871c2;
  color: white;
  margin-bottom: 3%;
  padding: 3px;
  border: 1px solid #1871c2;
  border-radius: 20px;
`;

export const FlexContainerStyles = styled.div`
  display: flex;
  margin: 5px;
`;

export const FlexItemLeftStyles = styled.div`
  flex: 1.3;
`;

export const FlexItemRightStyles = styled.div`
  flex: 1.2;
  text-align: right;
`;

export const Company = styled.p`
  font-size: 33px;
  padding-left: 15px;
  @media (max-width: 900px) {
    font-size: 15px;
    padding-left: 15px;
  }
`;

export const BuyDetails = styled.p`
  padding:8px;
  padding-right: 15px;
  padding-bottom: 8px;
  @media (max-width: 900px) {
    font-size: 11px;
    padding: 5px;
    padding-right: 15px;
}
`;

export const SellDetails = styled.p`
  padding: 15px;
  padding-top: 0;
  @media (max-width: 900px) {
    font-size: 11px;
    padding-bottom: 8px;
  }
`;

export const ProfitMargin = styled.p`
  padding: 15px;
  padding-top: 0;
  
  @media (max-width: 900px) {
    font-size: 11px;
    padding-top: 5px;
    padding-left: 15px;
  }
`;

