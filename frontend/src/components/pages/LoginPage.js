import React from 'react'
import { useSelector } from 'react-redux'
import LoginForm from './forms/LoginForm'

export default function LoginPage() {
    const userState = useSelector(state => state.user)

    return (
        <div>
            {
                userState === null ? 
                    <>
                        <h2>Login</h2>
                        <LoginForm />
                    </>
                    : 
                    <>
                        {userState.username} logged in
                    </>
            }
        </div>
    )
}
//<Field name="userField" component={userField} />
