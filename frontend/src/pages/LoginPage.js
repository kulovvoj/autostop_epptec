import React from 'react'
import { useSelector } from 'react-redux'
import UserForm from './forms/UserForm'

export default function LoginPage() {
    const usernameState = useSelector(state => state.username)

    return (
        <div>
            {
                usernameState.username === undefined ? 
                    <>
                        <h2>Login</h2>
                        <UserForm />
                    </>
                    : 
                    <>
                        {usernameState.username} logged in
                    </>
            }
        </div>
    )
}
//<Field name="userField" component={userField} />
