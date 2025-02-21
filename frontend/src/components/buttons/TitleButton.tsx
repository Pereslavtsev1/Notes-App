import { ReactNode, useState } from "react";
type TitleButtonProps = {
  title: string;
  leftIcon?: ReactNode;
  rightIcon?: ReactNode;
};
const TitleButton: React.FC<TitleButtonProps> = ({
  title,
  leftIcon,
  rightIcon,
}) => {
  const [isHovered, setIsHovered] = useState(false);
  return (
    <div
      className="flex items-center gap-3"
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      {leftIcon}
      <div className="flex w-full items-center justify-between">
        <button className="my-4 flex w-full cursor-pointer">
          <h1>{title}</h1>
        </button>
        {isHovered && rightIcon}
      </div>
    </div>
  );
};

export default TitleButton;
