import React, { } from 'react'
import LoginInput from './LoginInput'
import { reduxForm, Field } from 'redux-form'
import { loginAuth } from '../../../apiConsumer/auth'
import {loginAction, logoutAction} from '../../../actions/index'

// function loginCheck(username) {
//     const f = useEffect(() => fetch('http://localhost:8080/users/byUsername/' + username.username, {"Access-Control-Allow-Origin": "http://localhost:8080"})
//     .then(response => {
//         if (!response.ok) {
//             throw new Error('User not found');
//         }
//         return response.json();
//     })
//     .catch(error => {
//         console.error('Username lookup error', error)
//     }))
//     console.log("Returning obj:", f)
//     return f
// }

function login({username, dispatch}) {
    // const user = loginCheck(username)
    // if (user.username) {
    //     dispatch(loginAction(user))
    // }
    console.log(username)
    loginAuth(username)(dispatch)
}

function required(username) {
    if (!username || username === '') {
        return "This field is required";
    }

    return undefined;
}

const renderInput = ({input, meta}) => {
    return(
        <div>
            <LoginInput {...input} type="text" errorMessage={meta.touched && meta.error}/>
        </div>
    )
}

function LoginForm({handleSubmit, valid}) {
    return(
        <div>
            <form onSubmit={handleSubmit(login)}>
                <Field name="username" component={renderInput} type="text" validate={required} />
                <button disabled={!valid} type="submit">Log In</button>
            </form>
        </div>
    )
}

export default reduxForm({
    form: 'login-username-form',
    login,
})(LoginForm)
