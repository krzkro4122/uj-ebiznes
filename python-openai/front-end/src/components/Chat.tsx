import { useEffect, useState } from "react";

import ChatInput from "./ChatInput";
import ChatAnswers from "./ChatAnswers";
import ChatContext from "./ChatContext";
import { askOpenAi } from "../helpers/chatter";
import "../styles/Chat.css";

function Chat() {
  const [prompt, setPrompt] = useState<String>("");
  let [answers, setAnswers] = useState<String[]>([]);

  const handle = async () => {
    const answer = await askOpenAi(prompt);
    if (answer) setAnswers([...answers, answer]);
  };

  useEffect(() => {
    handle();
  }, [prompt]);

  return (
    <div className="chat">
      <ChatContext.Provider value={answers}>
        <ChatAnswers answers={answers} />
        <ChatInput setPrompt={setPrompt} />
      </ChatContext.Provider>
    </div>
  );
}

export default Chat;
