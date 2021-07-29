export function userReducer(state = null, action) {
    switch(action.type) {
        case 'LOGIN':
            return action.user
        case 'LOGOUT':
            return action.user
        default:
            return state
    }
}