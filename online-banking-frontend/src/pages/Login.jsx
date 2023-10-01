import React, { useContext, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Register.css";
import { Context } from "../context/Context";

const Login = () => {
  const history = useNavigate();

  const { dispatch, isFetching } = useContext(Context);
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(false);

  const handleLogin = async (e) => {
    e.preventDefault();
    dispatch({
      type: "LOGIN_START",
    });

    const loginData = {
      phoneNumber,
      password,
    };
    try {
      if (phoneNumber.match("admin@nexusbank.org") && password.match("admin")) {
        console.log("admin");
        dispatch({
          type: "LOGIN_SUCCESS",
          payload: "admin",
        });
        return history("/admin");
      }

      const response = await axios.post(
        "http://localhost:8085/loginUser",
        loginData,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
      );

      console.log(response);

      if (response.status === 200) {
        dispatch({
          type: "LOGIN_SUCCESS",
          payload: response.data.uid,
        });
        history("/");
      } else {
        setError(true);
        dispatch({
          type: "LOGIN_FAILURE",
        });
      }
    } catch (err) {
      setError(true);
      dispatch({
        type: "LOGIN_FAILURE",
      });
    }
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
              </form>
              {error && (
                <span className="loginError">Invalid credentials!</span>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
