import { FormEvent, useContext, useState } from "react";

import "../styles/ChatInput.css";

function ChatInput() {
  const [currentPrompt, setCurrentPrompt] = useState<String>();

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    if (currentPrompt) {
      // DO STH!!!
      alert(currentPrompt);
    }
  }

  return (
    <div className="chatInput">
      <form className="chatForm" onSubmit={handleSubmit}>
        <label>
          <input
            className="prompt"
            type="text"
            onChange={(event) => setCurrentPrompt(event.target.value)}
            placeholder="Prompt me!"
            autoFocus
          />
        </label>
        <button className="ask" type="submit">
          Ask
        </button>
      </form>
    </div>
  );
}

export default ChatInput;
