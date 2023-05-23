import { FormEvent, useState } from "react";

import "../styles/ChatAnswers.css";

interface IAnswer {
  answers: String[];
}

function ChatAnswers({ answers }: IAnswer) {
  const answersRedacted = answers.map((answer, index) => {
    return (
      <div key={index} className="answer">
        <b>{answer}</b>
      </div>
    );
  });
  return <div className="chatAnswers">{answersRedacted}</div>;
}

export default ChatAnswers;
