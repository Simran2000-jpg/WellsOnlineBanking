import React from "react";
import { Card, Button } from "react-bootstrap";
import "../styles/BankAccounts.css";
import { NavLink } from "react-router-dom";

const BankAccounts = () => {
  // Sample bank account data
  const bankAccounts = [
    {
      id: 1,
      accountName: "Savings Account",
      accountNumber: "1234567890",
      balance: 5000,
    },
    {
      id: 2,
      accountName: "Checking Account",
      accountNumber: "9876543210",
      balance: 2500,
    },
    {
      id: 3,
      accountName: "Investment Account",
      accountNumber: "5678901234",
      balance: 10000,
    },
  ];

  return (
    <div className="bank-account-page">
      <h2 className="page-title">My Bank Accounts</h2>
      <div className="account-cards">
        {bankAccounts.map((account) => (
          <Card key={account.id} className="account-card">
            <Card.Body>
              <Card.Title>{account.accountName}</Card.Title>
              <Card.Subtitle className="mb-2 text-muted">
                Account Number: {account.accountNumber}
              </Card.Subtitle>
              <Card.Text>
                <strong>Balance:</strong> ${account.balance.toFixed(2)}
              </Card.Text>
              <Button variant="primary">
                <NavLink to={"/dashboard/account-details"} style={{color: "white", textDecoration: "none"}}>
                  <i className="bi bi-person"></i> View Account
                </NavLink>
              </Button>
            </Card.Body>
          </Card>
        ))}
      </div>
    </div>
  );
};

export default BankAccounts;
