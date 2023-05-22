import { FormEvent, useContext, useState } from "react";

import "../styles/ChatInput.css";

async function handleSubmit(e: FormEvent<HTMLFormElement>) {
  e.preventDefault();
}

function ChatInput() {
  return (
    <div className="chatInput">
      <form className="chatForm" onSubmit={handleSubmit}>
        <label>
          <input type="text" placeholder="Username" autoFocus />
        </label>
        <div className="ask">
          <button type="submit">
            Ask
          </button>
        </div>
      </form>
    </div>
  );
}

export default ChatInput;
