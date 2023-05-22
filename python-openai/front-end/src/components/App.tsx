import { useState } from "react";

import TaskDashboard from "./TaskDashboard";
import Login from "./Login";
import "../styles/App.css";

function App() {
  const [token, setToken] = useState<String | undefined>();

  const getToken = () => {
    const token = "lol i am a token";
    return token;
  };

  return (
    <div className="App">
      {token ? (
        <TaskDashboard />
      ) : (
        <Login getToken={getToken} setToken={setToken} />
      )}
    </div>
  );
}

export default App;
