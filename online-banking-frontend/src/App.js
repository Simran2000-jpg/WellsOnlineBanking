import "./App.css";
import NavbarComponent from "./components/NavbarComponent";
import Home from "./pages/Home";

import "bootstrap-icons/font/bootstrap-icons.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Footer from "./components/Footer";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import OpenAccount from "./pages/OpenAccount";
import UserDasboard from "./pages/UserDashboard";
import SidebarComponent from "./components/SidebarComponent";

function App() {
  return (
    <>
      <Router>
        <NavbarComponent />
        <div className="App pt-5">
          <Routes>
            <Route path="/openaccount" element={<OpenAccount />} ></Route>
            <Route path="/login" element={<Login />} ></Route>
            <Route path="/register" element={<Register />} ></Route>
            <Route path="/" element={<Home />} ></Route>
            <Route path="/dashboard" element={<UserDasboard />} ></Route>
            <Route path="/dashboard/:xyz" element={<UserDasboard />} ></Route>
          </Routes>
        </div>
        <Footer />
      </Router>
    </>
  );
}

export default App;
