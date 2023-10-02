import React, { useContext, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Register.css";
import { Context } from "../context/Context";
import { toast } from "react-toastify";

const UpdatePassword = () => {
  const history = useNavigate();

  const { userId } = useContext(Context);
  const [oldLoginPassword, setOldLoginPassword] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [confirmLoginPassword, setConfirmLoginPassword] = useState("");
  const [error, setError] = useState(false);

  const validatePassword = (password) => {
    // Minimum 8 characters, at least one lowercase letter, one uppercase letter, one number, and one special character
    const passwordPattern =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordPattern.test(password);
  };

  const handleUpdatePassword = async (e) => {
    e.preventDefault();
    if (loginPassword !== confirmLoginPassword) {
      setError("Login password and confirm login password do not match.");
      return;
    }
    if (!validatePassword(loginPassword)) {
      setError(
        "Min 8 character password of lower, upper, numbers, and special characters"
      );
      return;
    }

    const updatePasswordData = {
      oldLoginPassword,
      newLoginPassword: loginPassword,
    };
    try {
      const response = await axios.put(
        "http://localhost:8085/updateLoginPassword/" + userId,
        updatePasswordData,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
      );

      console.log(response);

      if (response.status === 200) {
        toast.success("Login Password updated successfully");
        history("/");
      } else {
        setError(true);
        toast.error("Error updating login password");
      }
    } catch (err) {
      setError(true);
      console.error(err);
      if (err.response.status === 404) {
        toast.error("User not found");
      } else if (err.response.status === 401) {
        toast("🥷🏻 Current Login Password wrong");
      }
    }
  };

  return (
    <div className="form-bg my-5 mx-auto">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
            <div className="form-container">
              <h3 className="title">Update Login Password</h3>
              <form className="form-vertical">
                <div className="form-group">
                  <label>Current Login Password*</label>
                  <input
                    className="form-control"
                    type="password"
                    value={oldLoginPassword}
                    onChange={(e) => setOldLoginPassword(e.target.value)}
                    required
                  />
                </div>
                <div className="form-group">
                  <label>New Login Password*</label>
                  <input
                    className="form-control"
                    type="password"
                    value={loginPassword}
                    onChange={(e) => setLoginPassword(e.target.value)}
                    required
                  />
                </div>
                <div className="form-group">
                  <label>Confirm Login Password*</label>
                  <input
                    className="form-control"
                    type="password"
                    value={confirmLoginPassword}
                    onChange={(e) => setConfirmLoginPassword(e.target.value)}
                    required
                  />
                </div>
                <div className="text-center">
                  <button
                    className="my-2 mx-auto btn btn-success"
                    type="submit"
                    onClick={handleUpdatePassword}
                  >
                    Update Login Password
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

export default UpdatePassword;
