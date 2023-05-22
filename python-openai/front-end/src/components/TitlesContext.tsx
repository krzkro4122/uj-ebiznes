import { createContext } from "react";

const titles = [
  "This is a course, lol",
  "This is another course, lol",
  "This is a course, NOT lol",
  "This is a course, lol NOT",
  "This is NOT another course, lol",
];
const TitlesContext = createContext(titles);
export default TitlesContext;
