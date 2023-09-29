import React, { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Register.css";

const Login = () => {
  const history = useNavigate();

  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();

    const loginData = {
      phoneNumber,
      password,
    };
    const response = await axios.post(
      "http://localhost:8085/loginUser",
      loginData,
      {
        headers: { "Content-Type": "multipart/form-data" },
      }
    );

    const userResponse = await axios.get(
      "http://localhost:8085/users/phone/" + phoneNumber
    );

    localStorage.setItem("userId", userResponse.data.uid);
    window.dispatchEvent(new Event("storage"));

    if (response.data) history("/");
  };

  return (
    <div className="form-bg my-5 mx-auto">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
            <div className="form-container">
              <h3 className="title">Login</h3>
              <form className="form-vertical">
                <div className="form-group">
                  <label>Phone Number*</label>
                  <input
                    className="form-control"
                    type="text"
                    onChange={(e) => setPhoneNumber(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label>Login Password*</label>
                  <input
                    className="form-control"
                    type="text"
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
                <div className="text-center">
                  <button
                    className="my-2 mx-auto btn btn-success"
                    type="submit"
                    onClick={handleLogin}
                  >
                    Login
                  </button>
                </div>
                <span className="user-login mt-4">
                  Haven't registered for NetBanking yet? Click Here to{" "}
                  <a href="#">
                    <NavLink to={"/register"}>Register</NavLink>
                  </a>
                </span>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
