import React, { useEffect } from 'react'
import { Nav, NavLogo, NavLink, Bars, NavMenu, NavButtonLink, NavButton } from './NavbarElements'
import { useDispatch, useSelector } from 'react-redux'
import { logoutAction } from '../../actions'

export default function Navbar() {
  const userState = useSelector(state => state.user)
  const dispatch = useDispatch()
  return (
    <>
      <Nav>
        <NavLogo to="/">
          {"<d/>"}
        </NavLogo>
        <NavMenu>
          <NavLink to="/about">
            About
          </NavLink>

          <NavLink to="/contact">
            Contact
          </NavLink>
          {
            userState === null ?
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
                <NavButton onClick={() => dispatch(logoutAction())}>
                  Log Out
                </NavButton>
              </>
          }
        </NavMenu>
      </Nav>
    </>
  )
}
