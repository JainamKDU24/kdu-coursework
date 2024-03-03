import { Link } from "react-router-dom";
import * as styles from "../../styles/headerStyles"
import { useState } from "react";
import { LinkStyled } from "../../styles/dashboardStyles";

export function Header() {
  const [showDropdown, setShowDropdown] = useState(false);

  const toggleDropdown = () => {
    setShowDropdown(!showDropdown);
  };
  return (
    <div>
      <styles.HeaderContainer>
        <styles.AppInfo>
          <Link to="/">
            <styles.AppLogo
              xmlns="http://www.w3.org/2000/svg"
              id="Layer_1"
              data-name="Layer 1"
              viewBox="0 0 24 24"
              width="30"
              height="30"
            >
              <path d="M5,10.41L.29,5.71l1.41-1.41,3.29,3.29L12,.59l5,5L22.29,.29l1.41,1.41-6.71,6.71L12,3.41l-7,7Zm3,3.59h-2v10h2V14Zm-5-2H1v12H3V12Zm10-4h-2V24h2V8Zm5,3h-2v13h2V11Zm5-4h-2V24h2V7Z" />
            </styles.AppLogo>
          </Link>
          <styles.AppName>KDU Stock Market</styles.AppName>
        </styles.AppInfo>
        <styles.HeaderItems>
          <Link to="/summariser" style={{ textDecoration: 'none', color: '#f8f9fa' }}>
            <styles.HeaderItem>Summarizer</styles.HeaderItem>
          </Link>
          <Link to="/portfolio" style={{ textDecoration: 'none', color: '#f8f9fa' }}>
            <styles.HeaderItem>My Portfolio</styles.HeaderItem>
          </Link>
        </styles.HeaderItems>
        <styles.HamburgerMenu onClick={toggleDropdown}>
          <svg
            height="32px"
            id="Layer_1"
            style={{ background: 'new 0 0 32 32', cursor: 'pointer', fill:'white' }}
            version="1.1"
            viewBox="0 0 30 30"
            width="32px"
            xmlns="http://www.w3.org/2000/svg"
            xmlnsXlink="http://www.w3.org/1999/xlink"
          >
            <path d="M4,10h24c1.104,0,2-0.896,2-2s-0.896-2-2-2H4C2.896,6,2,6.896,2,8S2.896,10,4,10z M28,14H4c-1.104,0-2,0.896-2,2  s0.896,2,2,2h24c1.104,0,2-0.896,2-2S29.104,14,28,14z M28,22H4c-1.104,0-2,0.896-2,2s0.896,2,2,2h24c1.104,0,2-0.896,2-2  S29.104,22,28,22z" />
          </svg>
        </styles.HamburgerMenu>
        {showDropdown && (
          <styles.DropdownMenu>
            <LinkStyled to="/summariser" >
              <styles.DropdownItem style={{borderBottom: '1px solid black'}} onClick={toggleDropdown}>Summarizer</styles.DropdownItem>
            </LinkStyled>
            <LinkStyled to="/portfolio">
              <styles.DropdownItem onClick={toggleDropdown}>My Portfolio</styles.DropdownItem >
            </LinkStyled>
          </styles.DropdownMenu>
        )}      
        </styles.HeaderContainer>
    </div>
  );
}
