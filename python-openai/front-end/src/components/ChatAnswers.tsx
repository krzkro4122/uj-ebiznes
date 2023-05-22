import { FormEvent, useState } from "react";

import "../styles/ChatAnswers.css";

interface IAnswer {
  answers: string[]
}

function ChatAnswers({ answers }: IAnswer) {
  const answersRedacted = answers.map((answer) => {
    return (
      <div>
        <b>{answer}</b>
      </div>
    );
  });
  return (
    <div className="chatAnswers">
      {answersRedacted}
    </div>
  );
}

export default ChatAnswers;
