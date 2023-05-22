export type Username = String | undefined;
export type Password = String | undefined;
export type Email = String | undefined;
export type Token = String | undefined;

export function validateUsername(username: Username) {
    // TODO - username validation
    return !!username;
}

export function validatePassword(password: Password) {
    // TODO - password validation
    return !!password;
}

export function validateEmail(email: Email) {
    // TODO - password validation
    return !!email;
}

export function validateToken(token: Token) {
    // TODO - password validation
    return !!token;
}