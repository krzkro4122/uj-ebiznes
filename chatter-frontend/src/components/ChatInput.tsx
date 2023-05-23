import { FormEvent, useContext, useState } from "react";

import "../styles/ChatInput.css";

interface IPrompt {
  setPrompt: React.Dispatch<React.SetStateAction<String>>;
  isLoading: Boolean;
}

function ChatInput({ setPrompt, isLoading }: IPrompt) {
  const [currentPrompt, setCurrentPrompt] = useState<String>();

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    if (currentPrompt) {
      setPrompt(currentPrompt);
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
        <button className={`ask ${isLoading ? "loading" : ""}`} type="submit">
          Ask
        </button>
      </form>
    </div>
  );
}

export default ChatInput;
