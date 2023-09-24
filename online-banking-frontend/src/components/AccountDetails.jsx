import React, { useState, useEffect } from "react";
import "../styles/AccountDetails.css";

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
    <div className="account-details-container">
      <h2 className="account-details-title">Account Details</h2>
      <div className="account-info">
        <p>
          <strong>Account Number:</strong> {accountNumber}
        </p>
        <p>
          <strong>Account Name:</strong> {accountName}
        </p>
        <p>
          <strong>Account Balance:</strong> ${accountBalance.toFixed(2)}
        </p>
      </div>
    </div>
  );
};

export default AccountDetails;
