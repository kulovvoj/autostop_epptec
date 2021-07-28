export function usernameReducer(state = '', action) {
    switch(action.type) {
        case 'USERNAME':
            return action.username
        default:
            return state
    }
}