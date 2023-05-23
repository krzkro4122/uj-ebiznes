import { FormEvent, useState } from "react";
import "../styles/ChatAnswers.css";

interface IAnswerInfo {
  entries: {
    answer: String;
    prompt: String;
  }[];
}

function ChatAnswers({ entries }: IAnswerInfo) {
  const answersRedacted = entries.map((entry, index) => {
    return (
      <div key={index} className="answer">
        <b>{entry.prompt}</b>
        <br />
        <br />
        {entry.answer}
        <br />
      </div>
    );
  });
  return <div className="chatAnswers">{answersRedacted}</div>;
}

export default ChatAnswers;
