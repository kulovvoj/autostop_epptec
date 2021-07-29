export function loginAction(user) {
    return {
        type: 'LOGIN',
        user: user,
        isLogged: true
    }
}

export function logoutAction() {
    return {
        type: 'LOGOUT',
        user: null,
        isLogged: false
    }
}