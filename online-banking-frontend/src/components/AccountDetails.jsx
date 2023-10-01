import React, { useState, useEffect, useContext } from "react";
import "../styles/AccountDetails.css";
import Logo from "../images/user.jpg";
import AccountService from "../services/AccountService";
import { Context } from "../context/Context";
import UserService from "../services/UserService";

const AccountDetails = () => {
  const { userId, dispatch } = useContext(Context);

  const [selectedAccount, setSelectedAccount] = useState({
    accountNo: 0,
    balance: 0,
    ifscCode: "",
    isActive: false,
  });
  const [userDetails, setUserDetails] = useState({
    firstName: "",
    lastName: "",
    emailId: "",
    phoneNumber: "",
    dob: "",
    panNumber: "",
    aadharNumber: "",
    permanentAddress: {
      address: "",
      city: "",
      state: "",
      pincode: "",
    },
    residentialAddress: {
      address: "",
      city: "",
      state: "",
      pincode: "",
    },
    occupation: "",
    grossAnnualIncome: "",
    kyc: false,
  });

  const [accountDetails, setAccountDetails] = useState([
    {
      accountNo: 0,
      balance: 0,
      ifscCode: "",
      isActive: false,
    },
  ]);

  const fetchData = () => {
    UserService.getUserDetails(userId).then((response) => {
      console.log(response);
      setUserDetails({
        firstName: response.firstName,
        lastName: response.lastName,
        emailId: response.emailId,
        phoneNumber: response.phoneNumber,
        dob: response.dob,
        panNumber: response.panNumber,
        aadharNumber: response.aadharNumber,
        permanentAddress: {
          address: response.permanentAddress.address,
          city: response.permanentAddress.city,
          state: response.permanentAddress.state,
          pincode: response.permanentAddress.pincode,
        },
        residentialAddress: {
          address: response.residentialAddress.address,
          city: response.residentialAddress.city,
          state: response.residentialAddress.state,
          pincode: response.residentialAddress.pincode,
        },
        occupation: response.occupation,
        grossAnnualIncome: response.grossAnnualIncome,
        kyc: response.kyc,
      });
      AccountService.getAccountDetails(userId).then((response) => {
        console.log(response);
        if (response.length == 0) {
          console.log("No data found");
          return;
        } else {
          setAccountDetails(response);
          setSelectedAccount(response[0]);
        }
      });
    });
  };
  useEffect(() => {
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
                  <h2>{userDetails.firstName + " " + userDetails.lastName} </h2>
                </div>
                <div>
                  {userDetails.kyc ? (
                    <p className="alert alert-success"> Your KYC is Verified</p>
                  ) : (
                    <p className="alert alert-danger">
                      {" "}
                      Your KYC is not Verified, contact Admin{" "}
                    </p>
                  )}
                </div>
              </div>
            </div>
            <div className="col-4 ">
              <div className="w-30 h-100 bg-light p-4 d-flex flex-column justify-content-center">
                <div className="my-1">
                  <p className=" h6">
                    <b>Date of Birth</b>
                  </p>
                  <p className="">{userDetails.dob}</p>
                </div>
                <div className="my-1">
                  <p className=" h6">
                    <b>Email Address</b>
                  </p>
                  <p className="">{userDetails.emailId}</p>
                </div>
                <div className="my-1">
                  <p className=" h6">
                    <b>Aadhar Number</b>
                  </p>
                  <p className="">{userDetails.aadharNumber}</p>
                </div>
                <div className="my-1">
                  <p className=" h6">
                    <b>Occupation</b>
                  </p>
                  <p className="">{userDetails.occupation}</p>
                </div>

                <div className="my-1">
                  <p className=" h6">
                    <b>PAN Number</b>
                  </p>
                  <p className="">{userDetails.panNumber}</p>
                </div>
                <div className="my-1">
                  <p className=" h6">
                    <b>Phone Number</b>
                  </p>
                  <p className="">{userDetails.phoneNumber}</p>
                </div>
                <div className="my-1">
                  <p className=" h6">
                    <b>Gross Annual Income</b>
                  </p>
                  <p className="">{userDetails.grossAnnualIncome}</p>
                </div>
                <div className="my-1">
                  <p className=" h6">
                    <b>Permanent Address</b>
                  </p>
                  <p className="mt-0">{userDetails.permanentAddress.address}</p>
                  <p className="mt-0">{userDetails.permanentAddress.city}</p>
                  <p className="mt-0">{userDetails.permanentAddress.state}</p>
                  <p className="mt-0">{userDetails.permanentAddress.pincode}</p>
                </div>
              </div>
            </div>

            <div className=" col-4 flex-column d-flex justify-content-evenly">
              <div className="form-container d-flex flex-column align-items-between h-40">
                <p className="text-start h6">Account</p>
                <select
                  className="form-select"
                  aria-label="Default select example"
                  value={selectedAccount.accountNo}
                  selected={selectedAccount.accountNo}
                  onChange={(e) => {
                    console.log(e.target.value);
                    if (e.target.value == 0) {
                      return;
                    }
                    setSelectedAccount(
                      accountDetails.find(
                        (account) => account.accountNo == e.target.value
                      )
                    );
                  }}
                >
                  <option value={0}>Choose Account</option>
                  {accountDetails.map((account) => {
                    return (
                      <option value={account.accountNo}>
                        {account.accountNo}
                      </option>
                    );
                  })}
                </select>
              </div>

              <div className="form-container d-flex flex-column align-items-between h-40">
                <p className="text-start h6">Balance</p>
                <p className="text-start h2">₹ {selectedAccount.balance}</p>
              </div>
              <div className="form-container d-flex flex-column align-items-between h-40">
                <p className="text-start h6">Account Number</p>
                <p className="text-start h2">{selectedAccount.accountNo}</p>
              </div>
              <div className="form-container d-flex flex-column align-items-between h-40">
                <p className="text-start h6">IFSC Code</p>
                <p className="text-start h2">{selectedAccount.ifscCode}</p>
              </div>
              <div className="form-container d-flex flex-column align-items-between h-40">
                <p className="text-start h6">Account Status</p>
                <div className="text-start">
                  {selectedAccount.isActive ? (
                    <p className="alert alert-success">
                      {" "}
                      Your Account is Active
                    </p>
                  ) : (
                    <p className="alert alert-danger">
                      {" "}
                      Your Account is Inactive, contact Admin{" "}
                    </p>
                  )}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default AccountDetails;
