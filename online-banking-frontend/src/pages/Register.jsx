import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Register.css";

const Register = () => {
  const navigate = useNavigate();
  const [wasRegisterClicked, setWasRegisterClicked] = useState(false);
  const [accountNumber, setAccountNumber] = useState("");
  const [emailId, setEmail] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [confirmLoginPassword, setConfirmLoginPassword] = useState("");
  const [transactionPassword, setTransactionPassword] = useState("");
  const [confirmTransactionPassword, setConfirmTransactionPassword] = useState("");
  const [error, setError] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const areAllFieldsFilled = () => {
    return (
      accountNumber &&
      emailId &&
      loginPassword &&
      confirmLoginPassword &&
      transactionPassword &&
      confirmTransactionPassword
    )
  };

  const validatePassword = (password) => {
    // Minimum 8 characters, at least one lowercase letter, one uppercase letter, one number, and one special character
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordPattern.test(password);
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    if (loginPassword !== confirmLoginPassword) {
      setError("Login password and confirm login password do not match.");
      return;
    }
    if (transactionPassword !== confirmTransactionPassword) {
      setError("Transaction password and confirm transaction password do not match.");
      return;
    }
    if (!(validatePassword(loginPassword) && validatePassword(transactionPassword))) {
      setError("Min 8 character password of lower, upper, numbers, and special characters");
      return;
    }
    const registrationData = {
      accountNumber,
      emailId,
      loginPassword,
      transactionPassword,
    };
    try {
      const response = await axios.put("http://localhost:8085/register", registrationData, {
        headers: { "Content-Type": "multipart/form-data" }
      });
      if (response.status === 200) {
        setError(false);
        setSuccessMessage(response.data + "\nRedirecting...");
        setTimeout(() => {
          navigate("/login");
        }, 1000);
      } else {
        setError("Registration failed. Please try again.");
      }
    } catch (error) {
      console.log(error)
      setError(error.response.data);
    }
  };

  return (
    <div className="form-bg my-5 mx-auto">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
            <div className="form-container">
              <h3 className="title">Register</h3>
              {successMessage && <div className="alert alert-success"><label>{successMessage}</label></div>}
              {error && <div className="alert alert-danger"><label>{error}</label></div>}
              <form className="form-horizontal" onSubmit={handleRegister}>
                <div className="form-group">
                  <label>Account Number*</label>
                  <input
                    className="form-control"
                    type="text"
                    value={accountNumber}
                    onChange={(e) => setAccountNumber(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label>Email ID*</label>
                  <input
                    className="form-control"
                    type="email"
                    value={emailId}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label>Login Password*</label>
                  <input
                    className="form-control"
                    type="password"
                    value={loginPassword}
                    onChange={(e) => setLoginPassword(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label>Confirm Login Password*</label>
                  <input
                    className="form-control"
                    type="password"
                    value={confirmLoginPassword}
                    onChange={(e) => setConfirmLoginPassword(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label>Transaction Password*</label>
                  <input
                    className="form-control"
                    type="password"
                    value={transactionPassword}
                    onChange={(e) => setTransactionPassword(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label>Confirm Transaction Password*</label>
                  <input
                    className="form-control"
                    type="password"
                    value={confirmTransactionPassword}
                    onChange={(e) =>
                      setConfirmTransactionPassword(e.target.value)
                    }
                  />
                </div>
                {/* {wasRegisterClicked && (
                  <>
                    <h4 className="sub-title">Verification</h4>
                    <div className="form-group phone-no">
                      <label>Enter OTP</label>
                      <input className="form-control" type="text" />
                    </div>
                  </>
                )} */}
                <div className="text-center mb-4">
                  <button
                    className="my-2 mx-auto btn btn-success"
                    type="submit"
                    onClick={() => setWasRegisterClicked(true)}
                    disabled={!areAllFieldsFilled()}
                  >
                    Register
                  </button>
                </div>
                <span className="user-login ">
                  Already registered for NetBanking? Click Here to {" "}
                  <a href="#">
                    <NavLink to={'/login'}>
                      Login
                    </NavLink>
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

export default Register;
