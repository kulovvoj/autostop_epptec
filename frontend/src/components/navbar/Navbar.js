import React, {useEffect} from 'react'
import { Nav, NavLogo, NavLink, Bars, NavMenu, NavButtonLink, NavButton } from './NavbarElements'
import { useDispatch, useSelector } from 'react-redux'
import setUsername from '../../actions'

function logout(dispatch) {
  dispatch(setUsername(''))
}

export default function Navbar() {
  const username = useSelector(state => state.username)
  const dispatch = useDispatch()
  return (
    <>
      <Nav>
        <NavLogo to="/">
          {"<d/>"}
        </NavLogo>
        <Bars />
        <NavMenu>
          <NavLink to="/about">
            About
          </NavLink>

          <NavLink to="/contact">
            Contact
          </NavLink>
          {
            username === "" ?
              <>
                <NavButtonLink to="/login">
                  Log In
                </NavButtonLink>

                <NavButtonLink primary="true" to="/signup">
                  Sign Up
                </NavButtonLink>
              </> 
              :
              <>
                <NavButton onClick={() => logout(dispatch)}>
                  Log Out
                </NavButton>
              </>
          }
        </NavMenu>
      </Nav>
    </>
  )
}
