import styled from "styled-components";

export const MainHeader = styled.header`
`;

export const HeaderContainer = styled.div`
  padding: 1rem 1.25rem;
  background-color: #1971c2;
  border: 1px solid #294968;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const AppInfo = styled.div`
  display: flex;
  align-items: center;
  color: #f8f9fa;
`;

export const AppLogo = styled.svg`
  fill: white;
  margin-right: 1rem;
  @media (max-width: 900px) {
    margin-right: 0.6rem;
  }
`;

export const AppName = styled.span`
  font-size: 2rem;
  font-weight: bold;
  @media (max-width: 900px) {
    font-size: 1.8rem;
  }
`;

export const HeaderItems = styled.div`
  display: flex;
  align-items: center;
  font-size: 2rem;
  color: #f8f9fa;
  @media (max-width: 900px) {
    display: none;
  }
`;

export const HeaderItem = styled.div`
  margin-right: 2rem;
  text-decoration: none;
  color: #f8f9fa;
`;

export const HamburgerMenu = styled.div`
  display: none;
  font-size: 2rem;

  @media (max-width: 900px) {
    display: block;
    margin-top: 0.4rem;
    margin-left: 0.4rem;
    cursor: pointer;
    color: #f8f9fa;
  }
`;

export const DropdownMenu = styled.div`
  position: absolute;
  right: 1.25rem;
  top: 6rem;
  width: 12rem;
  z-index: 2;
  border: 2px solid black;
  border-radius: 25px;
  background-color: #e9ecef;

  @media (max-width: 393px) {
    top: 10rem;
  }
`;

export const DropdownItem = styled.div`
  padding: 1rem;
  cursor: pointer;
  font-size: 1.3rem;
  text-align: center;
`;


