import "../styles/Task.css";

interface ITaskInfo {
  taskId: number;
}

function Task({ taskId }: ITaskInfo) {
  return (
    <div className="task">
      <h1>{taskId}</h1>
    </div>
  );
}

export default Task;
