import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import "../styles/Register.css";
import Authy from 'authy';

const authy = require('authy')('296a94b91498fd87dc6f89ec2b7a27c4');
// const authy = new Authy();

const Register = () => {
  const [wasRegisterClicked, setWasRegisterClicked] = useState(false);
  const [registerFormDetails, setRegisterFormDetails] = useState({
    accountNumber: '',
    emailId: '',
    loginPassword: '',
    transactionPassword: ''
  })
  const [otp, setOTP] = useState('');
  const [verificationStatus, setVerificationStatus] = useState('');

  const onHandleChange = (event) => {
    setRegisterFormDetails({ [event.target.name]: event.target.value });
  }

  const handleSendOTP = async () => {
    try {
      // Generate and send OTP via email using Twilio Authy
      console.log(registerFormDetails.emailId);
      // const apiUrl = 'https://api.authy.com/protected/json/sms/rohithvepery8%40gmail.com?api_key=296a94b91498fd87dc6f89ec2b7a27c4?callback=myCallback';
      // jsonp(apiUrl, null, (err, response) => {
      //   if (err) {
      //     console.error('JSONP request error:', err);
      //   } else {
      //     console.log(response);
      //   }
      // });
      const response = await authy.request_sms(registerFormDetails.emailId, true);
      setVerificationStatus(`OTP sent to ${registerFormDetails.emailId}`);
    } catch (error) {
      console.error('Error sending OTP:', error);
      setVerificationStatus('Error sending OTP');
    }
  };

  const handleVerifyOTP = async () => {
    try {
      // Verify OTP entered by the user
      const response = await authy.verify(registerFormDetails.emailId, otp);
      if (response.success) {
        setVerificationStatus('OTP verified successfully');
      } else {
        setVerificationStatus('OTP verification failed');
      }
    } catch (error) {
      console.error('Error verifying OTP:', error);
      setVerificationStatus('Error verifying OTP');
    }
  };

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
                  <input className="form-control" name="emailId" type="email" onChange={onHandleChange} />
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
                    // type="submit"
                    onClick={(e) => {
                      e.preventDefault();
                      if (!wasRegisterClicked) { handleSendOTP() }
                      setWasRegisterClicked(true)
                    }}
                  >
                    Register
                  </button>
                </div>
                <p>{verificationStatus}</p>
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
