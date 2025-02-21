import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import Dashboard from "./pages/Dashboard";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Signin from "./pages/Signin";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/signin" element={<Signin />} />
          <Route path="/home" element={<Home />} />
          <Route path="/dashboard" element={[<Header />, <Dashboard />]} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
