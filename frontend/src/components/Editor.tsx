import { BlockNoteEditor, PartialBlock } from "@blocknote/core";
import "@blocknote/core/fonts/inter.css";
import { BlockNoteView } from "@blocknote/mantine";
import "@blocknote/mantine/style.css";
import {
  DragHandleButton,
  SideMenu,
  SideMenuController,
  useCreateBlockNote,
} from "@blocknote/react";
import React from "react";
import { RemoveBlockButton } from "./buttons/RemoveButton";

type EditorProps = {
  title?: string;
  onChange: (editor: BlockNoteEditor) => void;
  initialContent?: string;
  editable?: boolean;
};

const Editor: React.FC<EditorProps> = ({
  initialContent,
  editable,
  title,
  onChange,
}) => {
  let parsedContent: PartialBlock[] | undefined = undefined;

  try {
    parsedContent = initialContent
      ? (JSON.parse(initialContent) as PartialBlock[])
      : undefined;
  } catch (error) {
    console.error("Ошибка при разборе JSON:", error);
  }

  const editor: BlockNoteEditor = useCreateBlockNote({
    initialContent: parsedContent,
  });

  return (
    <div className="flex flex-col">
      <div className="px-5 py-4 text-2xl font-bold">
        {title ? title : "Unnamed"}
      </div>
      <div className="">
        <BlockNoteView
          editor={editor}
          onChange={() => onChange(editor)}
          sideMenu={false}
          editable={editable}
        >
          <SideMenuController
            sideMenu={(props) => (
              <SideMenu {...props}>
                <RemoveBlockButton {...props} />
                <DragHandleButton {...props} />
              </SideMenu>
            )}
          />
        </BlockNoteView>
      </div>
    </div>
  );
};

export default Editor;
