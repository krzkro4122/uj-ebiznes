import { useContext, useState } from "react";

import ChatInput from "./ChatInput"
import ChatAnswers from "./ChatAnswers"
import ChatContext from "./ChatContext";
import "../styles/Chat.css";

function Chat() {
  const answers = useContext(ChatContext);
  return (
    <div className="chat">
      <ChatContext.Provider value={answers}>
        <ChatAnswers answers={answers} />
        <ChatInput />
      </ChatContext.Provider>
    </div>
  );
}

export default Chat;
