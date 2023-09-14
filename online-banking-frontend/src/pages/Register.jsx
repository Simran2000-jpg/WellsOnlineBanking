import React from "react";
import "../styles/Register.css";
import { useState } from "react";

const Register = () => {
  const [wasRegisterClicked, setWasRegisterClicked] = useState(false);
  return (
    <div className="form-bg my-5 mx-auto">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
            <div className="form-container">
              <h3 className="title">Register</h3>
              <form className="form-horizontal">
                <div className="form-group">
                  <label>Account Number*</label>
                  <input className="form-control" type="text" />
                </div>
                <div className="form-group"> <label>Email ID*</label>
                  <input className="form-control" type="email" />
                </div>
                <div className="form-group">
                  <label>Login Password*</label>
                  <input className="form-control" type="password" />
                </div>
                <div className="form-group">
                  <label>Confirm Login Password*</label>
                  <input className="form-control" type="password" />
                </div>
                <div className="form-group">
                  <label>Transaction Password*</label>
                  <input className="form-control" type="password" />
                </div>
                <div className="form-group">
                  <label>Confirm Transaction Password*</label>
                  <input className="form-control" type="password" />
                </div>
                {wasRegisterClicked && (
                  <>
                    <h4 className="sub-title">Verification</h4>
                    <div className="form-group phone-no">
                      <label>Enter OTP</label>
                      <input className="form-control" type="text" />
                    </div>
                  </>
                )}
                <div className="text-center mb-4">
                  <button
                    className="my-2 mx-auto btn btn-success"
                    type="submit"
                    onClick={() => setWasRegisterClicked(true)}
                  >
                    Register
                  </button>
                </div>
                <span className="user-login ">
                  Already Have an Account? Click Here to <a href="#">Register</a>
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
