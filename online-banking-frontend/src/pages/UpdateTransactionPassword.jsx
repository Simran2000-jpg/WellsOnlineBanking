import React, { useContext, useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Register.css";
import { Context } from "../context/Context";
import { toast } from "react-toastify";
import UserService from "../services/UserService";
import AccountService from "../services/AccountService";

const UpdateTransactionPassword = () => {
  const history = useNavigate();

  const { userId } = useContext(Context);

  const [kyc, setKyc] = useState(false);
  const [fromAccount, setFromAccount] = useState("");
  const [fromAccountOptions, setFromAccountOptions] = useState([]);
  const [oldTransactionPassword, setOldTransactionPassword] = useState("");
  const [transactionPassword, setTransactionPassword] = useState("");
  const [confirmTransactionPassword, setConfirmTransactionPassword] =
    useState("");
  const [error, setError] = useState(false);

  useEffect(() => {
    UserService.getUserDetails(userId).then((response) => {
      setKyc(response.kyc);
    });

    AccountService.getAllFromAccounts(userId).then((response) => {
      console.log(response);
      if (response.status === 200) {
        setFromAccountOptions(response.data);
      } else {
        setError("Error fetching account options:");
        console.error("Error fetching account options:", error);
      }
    });
  }, []);

  const validatePassword = (password) => {
    // Minimum 8 characters, at least one lowercase letter, one uppercase letter, one number, and one special character
    const passwordPattern =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordPattern.test(password);
  };

  const handleUpdatePassword = async (e) => {
    e.preventDefault();
    if (transactionPassword !== confirmTransactionPassword) {
      setError("Login password and confirm transaction password do not match.");
      return;
    }
    if (!validatePassword(transactionPassword)) {
      setError(
        "Min 8 character password of lower, upper, numbers, and special characters"
      );
      return;
    }

    const updatePasswordData = {
      oldTransactionPassword,
      newTransactionPassword: transactionPassword,
    };
    try {
      const response = await axios.put(
        "http://localhost:8085/updateTransactionPassword/" + fromAccount,
        updatePasswordData,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
      );

      console.log(response);

      if (response.status === 200) {
        toast.success("Transaction Password updated successfully");
        history("/dashboard/account-details/" + fromAccount);
      } else {
        setError(true);
        toast.error("Error updating transaction password");
      }
    } catch (err) {
      setError(true);
      console.error(err);
      if (err.response.status === 404) {
        toast.error("Account not found");
      } else if (err.response.status === 401) {
        toast("🥷🏻 Current Transaction Password wrong");
      }
    }
  };

  return (
    <div className="form-bg my-5 mx-auto">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
            <div className="form-container">
              {kyc ? (
                <h3 className="title">Update Transaction Password</h3>
              ) : (
                <h3 className="title" style={{ color: "red" }}>
                  Contact Admin for KYC verificaion
                </h3>
              )}
              {kyc && (
                <form className="form-vertical">
                  <div className="form-group">
                    <label>Select Your Account*</label>
                    <select
                      className="form-select form-control"
                      value={fromAccount}
                      onChange={(e) => setFromAccount(e.target.value)}
                    >
                      <option value="">Select Your Account</option>
                      {fromAccountOptions.map(
                        (option) =>
                          option.transactionPassword && (
                            <option key={option.bid} value={option.accountNo}>
                              {option.accountNo}
                            </option>
                          )
                      )}
                    </select>
                  </div>
                  <div className="form-group">
                    <label>Current Transaction Password*</label>
                    <input
                      className="form-control"
                      type="password"
                      value={oldTransactionPassword}
                      onChange={(e) =>
                        setOldTransactionPassword(e.target.value)
                      }
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label>New Transaction Password*</label>
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
                  <div className="text-center">
                    <button
                      className="my-2 mx-auto btn btn-success"
                      type="submit"
                      onClick={handleUpdatePassword}
                    >
                      Update Transaction Password
                    </button>
                  </div>
                </form>
              )}
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

export default UpdateTransactionPassword;
