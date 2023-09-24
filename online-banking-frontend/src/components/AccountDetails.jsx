import React, { useState, useEffect } from "react";
import "../styles/AccountDetails.css"; // Import the CSS for styling

const AccountDetails = () => {
  // Define state variables to store account details
  const [accountNumber, setAccountNumber] = useState(""); // Replace with your initial account number
  const [accountName, setAccountName] = useState(""); // Replace with your initial account name
  const [accountBalance, setAccountBalance] = useState(0); // Replace with your initial account balance

  // Simulate fetching account details from an API
  useEffect(() => {
    // Replace this with your actual API call or data retrieval logic
    // For now, we'll simulate fetching data after a short delay
    const fetchData = async () => {
      // Simulate an API request delay
      await new Promise((resolve) => setTimeout(resolve, 1000));

      // Replace with actual data retrieval logic
      // For demonstration purposes, we'll set some sample data
      setAccountNumber("1234567890");
      setAccountName("John Doe");
      setAccountBalance(5000); // Example account balance
    };

    fetchData();
  }, []); // The empty dependency array ensures this runs only once when the component mounts

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
