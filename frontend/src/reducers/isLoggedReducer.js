export function isLoggedReducer(state = null, action) {
  switch(action.type) {
      case 'LOGIN':
          return true
      case 'LOGOUT':
          return false
      default:
          return state
  }
}