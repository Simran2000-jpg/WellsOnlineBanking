import React, { useState, useEffect } from "react";
import "../styles/AccountDetails.css";
import Logo from '../images/user.jpg'

const AccountDetails = () => {
  const [accountNumber, setAccountNumber] = useState("");
  const [accountName, setAccountName] = useState("");
  const [accountBalance, setAccountBalance] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      await new Promise((resolve) => setTimeout(resolve, 1000));
      setAccountNumber("1234567890");
      setAccountName("John Doe");
      setAccountBalance(5000);
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
                <img src={Logo} class='img-fluid' />
                <div>
                  <h2>{accountName}</h2>
                </div>
              </div>
            </div>
            <div className="col-4 ">
              <div className="w-30 h-100 bg-light p-4 d-flex flex-column justify-content-center">
                <div className='my-1'>
                  <p className=" h6"><b>Date of Birth</b></p>
                  <p className=''>2023-09-26</p>
                </div>
                <div className='my-1'>
                  <p className=" h6"><b>Email Address</b></p>
                  <p className=''>johndoe@gmail.com</p>
                </div>
                <div className='my-1'>
                  <p className=" h6"><b>PAN Number</b></p>
                  <p className=''>XHTVV6120H</p>
                </div>
                <div className='my-1'>
                  <p className=" h6"><b>Phone Number</b></p>
                  <p className=''>9187243561</p>
                </div>
              </div>
            </div>
            <div className=" col-4 flex-column d-flex justify-content-evenly">
              <div className="form-container d-flex flex-column align-items-between h-40">
                <p className="text-start h6">Balance</p>
                <p className="text-start h2">₹ {accountBalance}</p>
              </div>
              <div className="form-container d-flex flex-column align-items-between h-40">
                <p className="text-start h6">Account Number</p>
                <p className="text-start h2">{accountNumber}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default AccountDetails;
