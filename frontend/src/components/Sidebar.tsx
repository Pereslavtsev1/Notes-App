import React, { memo } from "react";
import { FaFolder, FaRegStar, FaTrash } from "react-icons/fa";
import { FaNoteSticky } from "react-icons/fa6";
import { GoPlus } from "react-icons/go";
import { IoIosSearch } from "react-icons/io";
import { IoHome } from "react-icons/io5";
import NoteButton from "./buttons/NoteButton";
import TitleButton from "./buttons/TitleButton";

type FolderSidebarProps = {
  workspaces: Workspace[];
  handleClick: (name: string) => void;
  handleUpdate: (workspaces: Workspace[]) => void;
};

export type Workspace = {
  id: string;
  name: string;
};

const FolderSidebar: React.FC<FolderSidebarProps> = ({
  workspaces,
  handleClick,
  handleUpdate,
}) => {
  return (
    <aside className={`fixed h-[90%] w-64 overflow-y-auto pl-4`}>
      <div className="">
        <div className="mx-7">
          <TitleButton
            leftIcon={<IoIosSearch className="size-5" />}
            title="Search"
          />
          <TitleButton leftIcon={<IoHome className="size-5" />} title="Home" />
          <TitleButton title="Trash" leftIcon=<FaTrash /> />
        </div>
      </div>
      <div className="text-white-light mt-3 flex flex-col gap-2">
        <TitleButton
          title="Favorites"
          leftIcon=<FaRegStar className="text-white-light" />
        />
        <TitleButton
          title="Workspace"
          leftIcon=<FaFolder className="text-white-light" />
          rightIcon=<GoPlus
            className="text-white-light size-6"
            onClick={() => {
              const newWorkspace: Workspace = {
                id: Math.random().toString(),
                name: "Unnamed",
              };
              handleUpdate([...workspaces, newWorkspace]);
            }}
          />
        />
        <div className="flex flex-col gap-3">
          {workspaces.map((workspace, index) => (
            <div className="w-60" key={index}>
              <NoteButton
                handleClick={() => handleClick(workspace.name)}
                title={workspace.name}
                leftIcon={<FaNoteSticky className="text-white-light" />}
              />
            </div>
          ))}
        </div>
      </div>
    </aside>
  );
};

export default memo(FolderSidebar);
