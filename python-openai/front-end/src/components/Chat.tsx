import { useContext, useState } from "react";

import ChatInput from "./ChatInput";
import ChatAnswers from "./ChatAnswers";
import ChatContext from "./ChatContext";
import "../styles/Chat.css";

function Chat() {
  const [prompt, setPrompt] = useState<String>();
  const answers = useContext(ChatContext);

  return (
    <div className="chat">
      <ChatContext.Provider value={answers}>
        <ChatAnswers answers={[prompt]} />
        <ChatInput setPrompt={setPrompt} />
      </ChatContext.Provider>
    </div>
  );
}

export default Chat;
