import { useContext, useState } from "react";

import ChatInput from "./ChatInput"
import "../styles/Chat.css";

function Chat() {
  return (
    <div className="chat">
      <ChatInput />
    </div>
  );
}

export default Chat;
