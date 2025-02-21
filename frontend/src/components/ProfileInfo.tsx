const ProfileInfo = () => {
  const getInitials = (name: string | undefined | null): string => {
    if (!name) return "";

    const words = name.split(" ");

    let res = "";

    for (let i = 0; i < Math.min(words.length, 2); i++) {
      res += words[i][0];
    }

    return res.toUpperCase();
  };

  return (
    <>
      <div className="flex items-center gap-10">
        <h1 className="text-xl font-semibold">{getInitials("John Doe")}</h1>
        <button
          className="cursor-pointer font-semibold"
          onClick={() => console.log("Logout")}
        >
          logout
        </button>
      </div>
    </>
  );
};

export default ProfileInfo;
