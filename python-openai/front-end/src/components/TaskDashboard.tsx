import { useContext, useState } from "react";

import TitlesContext from "./TitlesContext";
import "../styles/TaskDashboard.css";
import SideBar from "./SideBar";
import Task from "./Task";

function TaskDashboard() {
  const [activeTask, setActiveTask] = useState(0);
  const titles = useContext(TitlesContext);

  return (
    <div className="taskDashboard">
      <TitlesContext.Provider value={titles}>
        <SideBar activeTask={activeTask} setActiveTask={setActiveTask} />
        <Task taskId={activeTask} />
      </TitlesContext.Provider>
    </div>
  );
}

export default TaskDashboard;
