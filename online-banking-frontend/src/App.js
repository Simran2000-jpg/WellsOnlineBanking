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
import NotFound from "./pages/NotFound";
import { ToastContainer } from "react-toastify";

import "react-toastify/dist/ReactToastify.css";
import UpdatePassword from "./pages/UpdatePassword";

function App() {
  const { userId } = useContext(Context);
  console.log("app.js : ", userId);

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
                <Route path="/login/newUser" element={<Login />}></Route>
              </>
            ) : (
              <Route path="/" element={<Home />}></Route>
            )}
            {userId === "admin" && (
              <>
                <Route path="/admin" element={<Admin />}></Route>
                <Route path="/user/:id" element={<UserDetails />}></Route>
              </>
            )}

            {userId !== null ? (
              <>
              <Route path="/update-password" element={<UpdatePassword />}></Route>
                <Route path="/dashboard" element={<UserDasboard />}></Route>
                <Route
                  path="/dashboard/:xyz"
                  element={<UserDasboard />}
                ></Route>
                <Route
                  path="/dashboard/:xyz/:abc"
                  element={<UserDasboard />}
                ></Route>
                <Route path="/create-account" element={<AddAccount />}></Route>
              </>
            ) : (
              <Route path="/login" element={<Login />}></Route>
            )}
            <Route path="/" element={<Home />}></Route>
            <Route path="/dashboard/internet-banking" element={<NotFound />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </div>

        <Footer />
        <ToastContainer
          position="bottom-center"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="dark"
        />
      </Router>
    </>
  );
}

export default App;
