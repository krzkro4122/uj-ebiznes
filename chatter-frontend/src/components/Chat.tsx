import { useEffect, useState } from "react";

import ChatInput from "./ChatInput";
import ChatAnswers from "./ChatAnswers";
import ChatContext from "./ChatContext";
import { askOpenAi } from "../helpers/chatter";
import "../styles/Chat.css";

function Chat() {
  const [prompt, setPrompt] = useState<String>("");
  const [isLoading, setIsLoading] = useState<Boolean>(false);
  let [entries, setEntries] = useState<
    {
      answer: String;
      prompt: String;
    }[]
  >([]);

  const handle = async () => {
    setIsLoading(true);
    const answer = await askOpenAi(prompt);
    if (answer) setEntries([...entries, { answer: answer, prompt: prompt }]);
    setIsLoading(false);
  };

  useEffect(() => {
    handle();
  }, [prompt]);

  return (
    <div className="chat">
      <ChatContext.Provider value={entries}>
        <ChatAnswers entries={entries} />
        <ChatInput isLoading={isLoading} setPrompt={setPrompt} />
      </ChatContext.Provider>
    </div>
  );
}

export default Chat;
