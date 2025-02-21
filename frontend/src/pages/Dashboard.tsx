import { Block, BlockNoteEditor } from "@blocknote/core";
import { useState } from "react";
import Editor from "../components/Editor";
import Sidebar, { Workspace } from "../components/Sidebar";

const Dashboard = () => {
  const [workspaces, setWorkspaces] = useState<Workspace[]>([]);

  const onChange = (editor: BlockNoteEditor) => {
    const content: Block[] = editor.document;
    console.log(JSON.stringify(content));
  };
  const handleUpdateWorkspaces = (workspace: Workspace) => {
    setWorkspaces([...workspaces, workspace]);
  };
  return (
    <>
      <Sidebar
        workspaces={workspaces}
        handleClick={() => {}}
        handleUpdateWorkspaces={handleUpdateWorkspaces}
      />
      <div className="ml-72 px-10">
        <Editor initialContent="" onChange={onChange} />
      </div>
    </>
  );
};
export default Dashboard;
