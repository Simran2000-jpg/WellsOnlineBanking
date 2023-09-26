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
import Transaction from "./pages/Transaction";
import UserDasboard from "./pages/UserDashboard";
import SidebarComponent from "./components/SidebarComponent";
import { useEffect, useState } from "react";
import AddBeneficiary from "./pages/AddBeneficiary";
import ViewBeneficiary from "./pages/ViewBeneficiary";

function App() {
  const [user, setUser] = useState("");

  useEffect(() => {
    const onStorage = () => {
      setUser(localStorage.getItem("userId"));
    };
    window.addEventListener("storage", onStorage);

    return () => {
      window.removeEventListener("storage", onStorage);
    };
  });

  return (
    <>
      <Router>
        <NavbarComponent />
        <div className="App pt-5">
          <Routes>
            {user === null ? (
              <Route path="/openaccount" element={<OpenAccount />}></Route>
            ) : (
              <Route path="/" element={<Home />}></Route>
            )}

            {user === null ? (
              <Route path="/login" element={<Login />}></Route>
            ) : (
              <Route path="/" element={<Home />}></Route>
            )}

            {user !== null ? (
              <Route path="/register" element={<Register />}></Route>
            ) : (
              <Route path="/login" element={<Login />}></Route>
            )}

            {/* {user !== null ? (
              <Route path="/transaction" element={<Transaction />}></Route>
            ) : (
              <Route path="/login" element={<Login />}></Route>
            )} */}

            <Route path="/" element={<Home />}></Route>
            <Route path="/dashboard" element={<UserDasboard />}></Route>
            {user !== null ? (
              <Route path="/dashboard/:xyz" element={<UserDasboard />}></Route>
            ) : (
              <Route path="/login" element={<Login />}></Route>
            )}   
            {user &&   (<>    
            <Route path="/dashboard/add-beneficiary" element={<AddBeneficiary />} ></Route>
            <Route path="/dashboard/view-beneficiary" element={<ViewBeneficiary />} ></Route>
            </> 
            )
            }
          </Routes>
        </div>
        <Footer />
      </Router>
    </>
  );
}

export default App;
