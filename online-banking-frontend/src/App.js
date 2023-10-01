import "./App.css";
import NavbarComponent from "./components/NavbarComponent";
import Home from "./pages/Home";

import "bootstrap-icons/font/bootstrap-icons.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Footer from "./components/Footer";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import OpenAccount from "./pages/OpenAccount";
import Admin from "./pages/Admin";
import UserDetails from "./pages/UserDetails";
import UserDasboard from "./pages/UserDashboard";
import { useContext } from "react";
import { Context } from "./context/Context";
import AddAccount from "./pages/AddAccount";

function App() {
  const { userId } = useContext(Context);

  return (
    <>
      <Router>
        <NavbarComponent />
        <div className="App pt-5">
          <Routes>
            {userId === null ? (
              <>
                <Route path="/openaccount" element={<OpenAccount />}></Route>
                <Route path="/login" element={<Login />}></Route>
              </>
            ) : (
              <Route path="/" element={<Home />}></Route>
            )}

            {userId ? (
              <>
                <Route path="/dashboard" element={<UserDasboard />}></Route>
                <Route
                  path="/dashboard/:xyz"
                  element={<UserDasboard />}
                ></Route>
                <Route
                  path="/create-account"
                  element={<AddAccount />}
                ></Route>
              </>
            ) : (
              <Route path="/login" element={<Login />}></Route>
            )}

            <Route path="/admin" element={<Admin />}></Route>
            <Route path="/user/:id" element={<UserDetails />}></Route>

            <Route path="/" element={<Home />}></Route>
          </Routes>
        </div>
        <Footer />
      </Router>
    </>
  );
}

export default App;
