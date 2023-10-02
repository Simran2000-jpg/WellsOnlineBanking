import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import UserService from "../services/UserService";
import BeneficiaryService from "../services/BeneficiaryService";
import AccountService from "../services/AccountService";
import TransactionService from "../services/TransactionService";

function Transaction() {
  const userId = localStorage.getItem("userId");
  const navigate = useNavigate();

  const [kyc, setKyc] = useState(false);
  const [fromAccount, setFromAccount] = useState("");
  const [toAccount, setToAccount] = useState("");
  const [amount, setAmount] = useState();
  const [transactionPassword, setTransactionPassword] = useState("");
  const [remarks, setRemarks] = useState("");
  const [transactionType, setTransactionType] = useState("IMPS");
  const [toAccountOptions, setToAccountOptions] = useState([]);
  const [fromAccountOptions, setFromAccountOptions] = useState([]);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  useEffect(() => {
    UserService.getUserDetails(userId).then((response) => {
      setKyc(response.kyc);
    });

    BeneficiaryService.viewBeneficiary(userId).then((response) => {
      if (response.status === 200) {
        setToAccountOptions(response.data);
      } else {
        setError("Error fetching account options:");
      }
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

  const areAllFieldsFilled = () => {
    return (
      fromAccount !== "" &&
      toAccount !== "" &&
      amount !== "" &&
      transactionPassword !== ""
    );
  };

  const initiateTransaction = async () => {
    var transactionDetails = {
      amount: amount,
      transactionType: transactionType,
      transactionPassword: transactionPassword,
      remarks: remarks,
    };
    TransactionService.initiateTransaction(
      fromAccount,
      toAccount,
      transactionDetails
    ).then((response) => {
      if (response.status === 200) {
        setError(false);
        setSuccessMessage("Transaction Successful");
        setTimeout(() => {
          navigate("/dashboard/account-statement");
        }, 1000);
      } else {
        setError(response.response.data.message);
      }
    });
  };

  const handleTransaction = (e) => {
    e.preventDefault();
    initiateTransaction();
  };

  return (
    <div className="form-bg my-5 mx-auto">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
            <div className="form-container">
              {kyc ? (
                <h3 className="title">Transaction</h3>
              ) : (
                <h3 className="title" style={{ color: "red" }}>
                  Contact Admin for KYC verificaion
                </h3>
              )}
              {kyc && (
                <form className="form-vertical" onSubmit={handleTransaction}>
                  <div className="form-group">
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

                    <label>Transaction Type*</label>
                    <select
                      value={transactionType}
                      className="form-select form-control"
                      onChange={(e) => setTransactionType(e.target.value)}
                    >
                      <option value="IMPS">IMPS</option>
                      <option value="NEFT">NEFT</option>
                      <option value="RTGS">RTGS</option>
                    </select>
                  </div>
                  <div className="form-group">
                    <label>From Account*</label>
                    <select
                      className="form-select form-control"
                      value={fromAccount}
                      onChange={(e) => setFromAccount(e.target.value)}
                    >
                      <option value="">Select From Account</option>
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
                    <label>To Account*</label>
                    <select
                      className="form-select form-control"
                      value={toAccount}
                      onChange={(e) => setToAccount(e.target.value)}
                    >
                      <option value="">Select To Account</option>
                      {toAccountOptions.map(
                        (option) =>
                          option.ifscCode === "NX1845" && (
                            <option key={option.bid} value={option.accountNo}>
                              {option.accountNo}
                            </option>
                          )
                      )}
                    </select>
                  </div>
                  <div className="form-group">
                    <label>Amount*</label>
                    <input
                      className="form-control"
                      type="text"
                      value={amount}
                      onChange={(e) => setAmount(e.target.value)}
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
                    <label>Remarks</label>
                    <input
                      className="form-control"
                      type="text"
                      value={remarks}
                      onChange={(e) => setRemarks(e.target.value)}
                    />
                  </div>
                  <div className="text-center mb-4">
                    <button
                      className="my-4 mx-auto btn btn-success"
                      type="submit"
                      disabled={!areAllFieldsFilled()}
                    >
                      Initiate Transaction
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
}

export default Transaction;
