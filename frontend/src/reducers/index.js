import { usernameReducer } from "./usernameReducer";
import { combineReducers } from "redux";
import { reducer as formReducer } from "redux-form"

const combinedReducers = combineReducers({
    username: usernameReducer,
    form: formReducer
})

export default combinedReducers;