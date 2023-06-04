export type Username = String | undefined;
export type Password = String | undefined;
export type Email = String | undefined;
export type Token = String | undefined;

export const SERVER_URL = "http://localhost:8080";

export interface TokenInfo {
    created_at: string,
    valid_until: string,
    token: string,
}

export function validateUsername(username: Username) {
    // TODO - username validation
    return !!username;
}

export function validatePassword(password: Password) {
    // TODO - password validation
    return !!password;
}

export async function getToken (username: Username, password: Password): Promise<Token> {
    const response = await fetch(`${SERVER_URL}${"/login"}`, {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: JSON.stringify({
        username: username,
        password: password,
      }),
    });
    const jsonData: TokenInfo = await response.json();
    const token = jsonData.token;
    return token;
  };