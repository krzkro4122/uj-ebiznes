import { createContext } from "react";

const titles = [
  "Some random",
  "Random things",
  "Things on the",
  "The sidebar",
  "LOL!",
];
const TitlesContext = createContext(titles);
export default TitlesContext;
