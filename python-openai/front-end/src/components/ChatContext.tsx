import { createContext } from "react";

const answers = [
  "Some random",
  "Random things",
  "Things on the",
  "The sidebar",
  "LOL!",
];
const ChatContext = createContext(answers);
export default ChatContext;
