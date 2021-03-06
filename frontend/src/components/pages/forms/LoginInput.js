import React from 'react'

export default function LoginInput({errorMessage, ...props}) {
    return (
        <div className="input-text">
            <input {...props} /><br/>
            {errorMessage && <span className="errorMessage">{errorMessage}</span>}
        </div>
    )
}
