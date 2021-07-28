import styled from 'styled-components'
import { NavLink as Link } from 'react-router-dom'
import { FaBars } from 'react-icons/fa'

export const Nav = styled.nav`
  background: #382137;
  height: 60px;
  display: flex;
  justify-content: space-between;
  padding: 0.5rem calc((100vw - 1000px) / 2);
  z-index: 20;
`

export const NavLink = styled(Link)`
  color: #4f3b48;
  display: flex;
  margin: 5px;
  align-items: center;
  text-decoration: none;
  padding: 0 1rem;
  height: 100%;
  cursor: pointer;
  border-radius: 5px;
  border: 2px solid #4f3b48;

  &:visited {
    color: #ccc;
  }

  &.active {
    color: #ccc;
    
    border: 2px solid #ccc;
  }
`

export const NavLogo = styled(Link)`
  font-size: 32px;
  font-weight: bold;
  font-family: 'Cascadia code', Consolas, 'Courier New', monospace;
  background-color: #63a77b;
  border-radius: 5px;
  color: #643a62;
  display: flex;
  align-items: center;
  font-style: italic;
  text-decoration: none;
  padding: 0 1rem;
  margin-left: 24px;
  height: 100%;
  cursor: pointer;  

  &:visited {
    color: #643a62;
  }
`

export const Bars = styled(FaBars)`
  display: none;
  color: #fff;

  @media screen and (max-width: 768px) {
    display: block;
    position: absolute;
    top: 40px;
    right: 50px;
    transform: translate(-100%, 75%);
    font-size: 1.8rem;
    cursor: pointer;
  }
`

export const NavButtonLink = styled(Link)`
  color:  ${props => props.primary ? "#ccc" : "#4f3b48"};
  background-color:  ${props => props.primary ? "#4f3b48" : "#ccc"};
  border: ${props => props.primary ? "2px solid #ccc" : "2px solid #4f3b48"};
  
  display: flex;
  margin: 5px;
  align-items: center;
  text-decoration: none;
  padding: 0 1rem;
  height: 100%;
  cursor: pointer;
  border-radius: 5px;
  border: 2px solid #4f3b48;
`

export const NavButton = styled.button`
  color:  ${props => props.primary ? "#ccc" : "#4f3b48"};
  background-color:  ${props => props.primary ? "#4f3b48" : "#ccc"};
  border: ${props => props.primary ? "2px solid #ccc" : "2px solid #4f3b48"};
  
  display: flex;
  margin: 5px;
  align-items: center;
  text-decoration: none;
  padding: 0 1rem;
  height: 100%;
  cursor: pointer;
  border-radius: 5px;
  border: 2px solid #4f3b48;
`

export const NavMenu = styled.div`
  display: flex;
  align-items: center;
  margin-right: 24px;

  @media screen and (max-width: 768px) {
    display: none;
  }
`
