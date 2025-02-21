import { useState } from "react";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import { Link } from "react-router-dom";

const Login = () => {
  const [showPassword, setShowPassword] = useState(false);

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  return (
    <div className="bg-midnight flex min-h-svh w-full items-center justify-center rounded p-6 md:p-10">
      <div className="border-white-light w-full max-w-sm rounded-2xl border p-10">
        <div className="flex flex-col gap-6">
          <div className="flex flex-col gap-4">
            <h1 className="text-2xl font-bold">Login</h1>
            <p className="text-sm text-white/50">
              Enter your email below to login in your account
            </p>
          </div>
          <form>
            <div className="flex flex-col gap-6">
              <div className="flex flex-col gap-2">
                <label htmlFor="email">Email</label>
                <input
                  className="base-input"
                  id="email"
                  type="email"
                  placeholder="m@example.com"
                  required
                />
              </div>
              <div className="flex flex-col gap-2">
                <div className="flex items-center justify-between">
                  <label htmlFor="password">Password</label>
                  <button
                    type="button"
                    className="text-white-light cursor-pointer text-sm"
                    onClick={togglePasswordVisibility}
                  >
                    {showPassword ? (
                      <FaEyeSlash className="h-5 w-5" />
                    ) : (
                      <FaEye className="h-5 w-5" />
                    )}
                  </button>
                </div>
                <input
                  className="base-input"
                  id="password"
                  type={showPassword ? "text" : "password"}
                  required
                />
              </div>
              <button
                type="submit"
                className="text-midnight w-full cursor-pointer rounded-md border border-transparent bg-white px-4 py-2 text-sm font-medium shadow-sm"
              >
                Login
              </button>
            </div>
          </form>
          <div className="flex items-center justify-center gap-2">
            <p className="text-sm">Don't have an account?</p>
            <Link className="cursor-pointer text-sm no-underline" to="/signup">
              Sign up
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
