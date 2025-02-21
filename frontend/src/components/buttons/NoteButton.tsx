import React, { ReactNode } from "react";

type ButtonProps = {
  handleClick: () => void;
  title: string;
  containerClass?: string;
  leftIcon?: ReactNode;
  rightIcon?: ReactNode;
};

const NoteButton: React.FC<ButtonProps> = ({
  handleClick,
  title,
  leftIcon,
  rightIcon,
  containerClass,
}) => {
  return (
    <button
      className={`${containerClass} base-button flex gap-3`}
      onClick={handleClick}
    >
      {leftIcon}
      <h1 className="text-sm font-normal">{title}</h1>
      {rightIcon}
    </button>
  );
};

export default NoteButton;
