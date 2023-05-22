import { FormEvent, useState } from "react";

import "../styles/ChatAnswers.css";

interface IAnswer {
  answers: (String | undefined)[];
}

function ChatAnswers({ answers }: IAnswer) {
  const answersRedacted = answers.map((answer) => {
    return (
      <div>
        <b>{answer}</b>
      </div>
    );
  });
  return <div className="chatAnswers">{answersRedacted}</div>;
}

export default ChatAnswers;
