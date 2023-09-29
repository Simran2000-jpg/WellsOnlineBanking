import React, { useState, useEffect } from "react";
import "../styles/AccountDetails.css";
import Logo from "../images/user.jpg";
import AccountService from "../services/AccountService";

const AccountDetails = () => {
  const userId = localStorage.getItem('userId')
  const [accountDetails, setAccountDetails] = useState({
    accountNumber: '',
    accountName: '',
    accountBalance: 0,
    dob: '',
    email: '',
    panNumber: 0,
    phoneNumber: 0
  })

  useEffect(() => {
    const fetchData = () => {
      AccountService.getAccountDetails(userId).then((response)=> {
        setAccountDetails({
          accountNumber: response[0].accountNo,
          accountName: response[0].user.firstName,
          accountBalance: response[0].balance,
          dob: response[0].user.dob,
          email: response[0].user.emailId,
          panNumber: response[0].user.panNumber,
          phoneNumber: response[0].user.phoneNumber
        })
      })
    };
    fetchData();
  }, []);

  return (
    <>
      <div className="form-container mt-5 margin-left-sidebar">
        <h3 className="title">Account Details</h3>
        <div className="container">
          <div className="row">
            <div className="col-4">
              <div>
                <img src={Logo} className="img-fluid" />
                <div>
                  <h2>{accountDetails.accountName}</h2>
                </div>
              </div>
            </div>
            <div className="col-4 ">
              <div className="w-30 h-100 bg-light p-4 d-flex flex-column justify-content-center">
                <div className="my-1">
                  <p className=" h6">
                    <b>Date of Birth</b>
                  </p>
                  <p className="">{accountDetails.dob}</p>
                </div>
                <div className="my-1">
                  <p className=" h6">
                    <b>Email Address</b>
                  </p>
                  <p className="">{accountDetails.email}</p>
                </div>
                <div className="my-1">
                  <p className=" h6">
                    <b>PAN Number</b>
                  </p>
                  <p className="">{accountDetails.panNumber}</p>
                </div>
                <div className="my-1">
                  <p className=" h6">
                    <b>Phone Number</b>
                  </p>
                  <p className="">{accountDetails.phoneNumber}</p>
                </div>
              </div>
            </div>
            <div className=" col-4 flex-column d-flex justify-content-evenly">
              <div className="form-container d-flex flex-column align-items-between h-40">
                <p className="text-start h6">Balance</p>
                <p className="text-start h2">₹ {accountDetails.accountBalance}</p>
              </div>
              <div className="form-container d-flex flex-column align-items-between h-40">
                <p className="text-start h6">Account Number</p>
                <p className="text-start h2">{accountDetails.accountNumber}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default AccountDetails;
