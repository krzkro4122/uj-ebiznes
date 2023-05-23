import { createContext, useState } from "react";

const answers: Array<String | undefined> = [];
const ChatContext = createContext(answers);
export default ChatContext;
