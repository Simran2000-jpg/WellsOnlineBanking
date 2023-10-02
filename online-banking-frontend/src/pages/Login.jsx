import React, { useContext, useState } from "react";
import { NavLink, useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Register.css";
import { Context } from "../context/Context";
import { toast } from "react-toastify";

const Login = () => {
  const history = useNavigate();

  const location = useLocation();
  const path = location.pathname.split("/");

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
        toast("🧑🏻‍💻Logged in as Admin");
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
        toast.success("Logged in successfully");

        if (response.data.kyc === false) {
          toast.info(
            "KYC is still pending, contact admin for verification as you won't be able to use all the functionality"
          );
        }
        if(path[2] === "newUser")
          history("/update-password");
        else
          history("/");
      } else {
        setError(true);
        dispatch({
          type: "LOGIN_FAILURE",
        });
      }
    } catch (err) {
      setError(true);
      console.error(err);
      if (err.response.status === 404) {
        toast.error("User not found");
      } else if (err.response.status === 401) {
        toast("🥷🏻 Invalid credentials");
      }
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
              {/* {error && (
                <span className="loginError">Invalid credentials!</span>
              )} */}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
