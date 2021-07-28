import React, {useEffect} from 'react'
import Input from './Input'
import { reduxForm, Field } from 'redux-form'
import setUsername from '../../actions/index'


function submit(username, dispatch) {
    console.log(username)
    if (username) {
        return dispatch(setUsername(username))
    }
}

function required(username) {
    if (!username || username === '') {
        console.log("req")
        return "This field is required";
    }

    return undefined;
}

const renderInput = ({input, meta}) => {
    return(
        <div>
            <Input {...input} type="text" errorMessage={meta.touched && meta.error}/>
        </div>
    )
}

function UserForm({handleSubmit, valid}) {
    return(
        <div>
            <form onSubmit={handleSubmit(submit)}>
                <Field name="username" component={renderInput} type="text" validate={required} />
                <button disabled={!valid} type="submit">Log In</button>
            </form>
        </div>
    )
}

export default reduxForm({
    form: 'login-username-form',
    submit,
})(UserForm)
