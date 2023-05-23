import { createContext, useState } from "react";

const entries: {
  answer: String;
  prompt: String;
}[] = [];
const ChatContext = createContext(entries);
export default ChatContext;
