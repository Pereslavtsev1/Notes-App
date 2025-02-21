import { useState } from "react";
import Sidebar from "../components/Sidebar";

const Dashboard = () => {
  const [workspaces, setWorkspaces] = useState([]);
  return (
    <>
      <Sidebar
        workspaces={workspaces}
        handleClick={() => {}}
        handleUpdate={(workspaces) => {
          console.log(workspaces);
        }}
      />
    </>
  );
};
export default Dashboard;
