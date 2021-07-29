import { loginAction } from "../actions"
import authService from "../services/authService"

export const loginAuth = (username) => (dispatch) => {
  return authService.login(username).then(
    (response) => {
      dispatch(loginAction(response))
    }, 
    (error) => {
      const message =
        (error.response &&
          error.response.data &&
          error.response.data.message) ||
        error.message ||
        error.toString();
    }
  )
}