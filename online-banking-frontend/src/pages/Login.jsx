import React from "react";
import "../styles/Register.css";

const Login = () => {
  return (
    <div className="form-bg my-5 mx-auto">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
            <div className="form-container">
              <h3 className="title">Login</h3>
              <form className="form-vertical">
                <div className="form-group">
                  <label>Customer ID*</label>
                  <input className="form-control" type="text" />
                </div>
                <div className="form-group">
                  <label>Login Password*</label>
                  <input className="form-control" type="email" />
                </div>
                <div className="text-center">
                  <button class="my-2 mx-auto btn btn-success" type="submit">
                    Login
                  </button>
                </div>
                <span className="user-login mt-4">
                  Haven't registered for NetBanking yet? Click Here to <a href="#">Register</a>
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
