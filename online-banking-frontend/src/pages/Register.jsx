import React, { useContext, useEffect, useState } from "react";
import { NavLink, useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import "../styles/Register.css";
import RegisterService from "../services/RegisterService";
import AccountService from "../services/AccountService";
import { Context } from "../context/Context";
import UserService from "../services/UserService";

const Register = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const path = location.pathname.split("/");

  const { userId, dispatch } = useContext(Context);

  const [kyc, setKyc] = useState(false);
  const [selectedAccount, setSelectedAccount] = useState({
    accountNo: 0,
    balance: 0,
    ifscCode: "",
    transactionPassword: null,
    isActive: false,
  });

  const [emailId, setEmail] = useState("");
  const [transactionPassword, setTransactionPassword] = useState("");
  const [confirmTransactionPassword, setConfirmTransactionPassword] =
    useState("");
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const fetchData = () => {
    UserService.getUserDetails(userId).then((response) => {
      setKyc(response.kyc);
    });
    AccountService.getAccountDetails(userId).then((response) => {
      if (response.length == 0) {
        console.log("No data found");
        return;
      } else {
        if (path[3]) {
          response.map((account) =>
            account.accountNo == path[3] ? setSelectedAccount(account) : null
          );
        }
      }
    });
  };
  useEffect(() => {
    fetchData();
  }, []);

  const validatePassword = (password) => {
    // Minimum 8 characters, at least one lowercase letter, one uppercase letter, one number, and one special character
    const passwordPattern =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordPattern.test(password);
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    if (transactionPassword !== confirmTransactionPassword) {
      setError(
        "Transaction password and confirm transaction password do not match."
      );
      return;
    }
    if (!validatePassword(transactionPassword)) {
      setError(
        "Min 8 character password of lower, upper, numbers, and special characters"
      );
      return;
    }
    const registrationData = {
      accountNumber: selectedAccount.accountNo,
      emailId,
      transactionPassword,
    };

    RegisterService.registerInternetBanking(registrationData).then(
      (response) => {
        if (response.status === 200) {
          setError(false);
          setSuccessMessage(response.data + "\nRedirecting...");
          setTimeout(() => {
            navigate("/dashboard/account-details/" + selectedAccount.accountNo);
          }, 1000);
        } else {
          console.log(response);
          setError(response.response.data);
        }
      }
    );
  };

  return (
    <div className="form-bg my-5 mx-auto">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
            <div className="form-container">
              <h3 className="title" style={{ marginBottom: "30px" }}>
                {!selectedAccount.transactionPassword ? (
                  kyc ? (
                    "Register for Internet Banking"
                  ) : (
                    <>
                      <div style={{ color: "red" }}>
                        Contact Admin for KYC verification
                      </div>
                    </>
                  )
                ) : (
                  "Already Registered for Internet Banking, thanks!"
                )}
              </h3>
              {successMessage && (
                <div className="alert alert-success">
                  <label>{successMessage}</label>
                </div>
              )}
              {error && (
                <div className="alert alert-danger">
                  <label>{error}</label>
                </div>
              )}
              {kyc && !selectedAccount.transactionPassword && (
                <form className="form-horizontal" onSubmit={handleRegister}>
                  <div className="form-group">
                    <label>Account Number*</label>
                    <input
                      className="form-control"
                      type="text"
                      value={selectedAccount.accountNo}
                      disabled
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label>Email ID*</label>
                    <input
                      className="form-control"
                      type="email"
                      value={emailId}
                      onChange={(e) => setEmail(e.target.value)}
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label>Transaction Password*</label>
                    <input
                      className="form-control"
                      type="password"
                      value={transactionPassword}
                      onChange={(e) => setTransactionPassword(e.target.value)}
                      required
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
                      required
                    />
                  </div>
                  <div className="text-center mb-3">
                    <button
                      className="my-2 mx-auto btn btn-success"
                      type="submit"
                      onClick={() => {}}
                    >
                      Register
                    </button>
                  </div>
                </form>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
