import { MdEditNote } from "react-icons/md";
import ProfileInfo from "./ProfileInfo";

const Header = () => {
  return (
    <>
      <header className="bg-primary sticky top-0 z-50 flex items-center justify-between px-14 py-4 shadow">
        <div className="flex items-center gap-2">
          <MdEditNote className="size-10" />
          <h1 className="text-2xl font-bold">Cheet Sheet App</h1>
        </div>
        <ProfileInfo />
      </header>
    </>
  );
};

export default Header;
