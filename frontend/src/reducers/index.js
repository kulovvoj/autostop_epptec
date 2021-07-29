import { userReducer } from "./userReducer";
import { isLoggedReducer } from "./isLoggedReducer";
import { combineReducers } from "redux";
import { reducer as formReducer } from "redux-form"

const combinedReducers = combineReducers({
    user: userReducer,
    isLogged: isLoggedReducer,
    form: formReducer
})

export default combinedReducers;