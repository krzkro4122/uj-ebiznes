import { useState } from "react";

import Chat from "./Chat";
import Login from "./Login";
import { Token, getToken } from "../helpers/validation";
import "../styles/App.css";

function App() {
  const [token, setToken] = useState<Token>();

  return (
    <div className="App">
      {token ? <Chat /> : <Login getToken={getToken} setToken={setToken} />}
    </div>
  );
}

export default App;
