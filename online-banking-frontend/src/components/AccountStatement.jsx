import React, { useState, useEffect } from "react";
import { Button, Form, Table } from "react-bootstrap";
import "../styles/AccountStatement.css"; // Import the CSS for styling

const AccountStatement = () => {
  const [accounts, setAccounts] = useState([]);
  const [selectedAccount, setSelectedAccount] = useState("");
  const [transactions, setTransactions] = useState([]);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [fromAccount, setFromAccount] = useState("");
  const [toAccount, setToAccount] = useState("");
  const [sortByDate, setSortByDate] = useState(false);

  // Simulate fetching account list from an API
  useEffect(() => {
    // Replace this with your actual API call or data retrieval logic
    // For now, we'll simulate fetching data after a short delay
    const fetchAccounts = async () => {
      // Simulate an API request delay
      await new Promise((resolve) => setTimeout(resolve, 1000));

      // Replace with actual data retrieval logic
      // For demonstration purposes, we'll set some sample account data
      const accountList = ["Account 1", "Account 2", "Account 3"];
      setAccounts(accountList);
    };

    fetchAccounts();
  }, []);

  // Simulate fetching transactions for the selected account
  useEffect(() => {
    // Replace this with your actual API call or data retrieval logic
    // For demonstration purposes, we'll set some sample transaction data
    const transactionList = [
      {
        date: "2023-09-01",
        from: "Account 1",
        to: "Account 2",
        description: "Payment received",
        amount: 1000,
      },
      {
        date: "2023-09-05",
        from: "Account 2",
        to: "Account 3",
        description: "Grocery shopping",
        amount: -200,
      },
      {
        date: "2023-09-10",
        from: "Account 1",
        to: "Account 2",
        description: "Salary deposit",
        amount: 2500,
      },
    ];

    setTransactions(transactionList);
  }, [selectedAccount]);

  // Function to filter and sort transactions based on user input
  const filterTransactions = () => {
    // Replace with your filtering and sorting logic based on user input
    // For now, we'll set filtered and sorted transactions as an example
    let filteredTransactions = [...transactions];

    if (startDate && endDate) {
      filteredTransactions = filteredTransactions.filter(
        (transaction) =>
          transaction.date >= startDate && transaction.date <= endDate
      );
    }

    if (fromAccount) {
      filteredTransactions = filteredTransactions.filter(
        (transaction) => transaction.from === fromAccount
      );
    }

    if (toAccount) {
      filteredTransactions = filteredTransactions.filter(
        (transaction) => transaction.to === toAccount
      );
    }

    if (sortByDate) {
      filteredTransactions.sort((a, b) => a.date.localeCompare(b.date));
    }

    setTransactions(filteredTransactions);
  };

  return (
    <div className="account-statement-container">
      <h2 className="account-statement-title">Account Statement</h2>

      {/* Account selection */}
      <Form>
        <Form.Group>
          <Form.Label>Select Account:</Form.Label>
          <Form.Control
            as="select"
            onChange={(e) => setSelectedAccount(e.target.value)}
          >
            <option value="">Select an account</option>
            {accounts.map((account, index) => (
              <option key={index} value={account}>
                {account}
              </option>
            ))}
          </Form.Control>
        </Form.Group>
      </Form>

      {/* Filter and sort options */}
      <Form>
        <div style={{display:"inline-flex", width:"100%"}}>
        <Form.Group>
          <Form.Label>Start Date:</Form.Label>
          <Form.Control
            type="date"
            value={startDate}
            onChange={(e) => setStartDate(e.target.value)}
            style={{marginLeft:"0px", width:"380px"}}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>End Date:</Form.Label>
          <Form.Control
            type="date"
            value={endDate}
            onChange={(e) => setEndDate(e.target.value)}
            style={{width:"380px"}}
          />
        </Form.Group>
        </div>
        <div style={{display:"inline-flex", width:"100%"}}>
        <Form.Group>
          <Form.Label>From Account:</Form.Label>
          <Form.Control
            type="text"
            value={fromAccount}
            onChange={(e) => setFromAccount(e.target.value)}
            style={{marginLeft:"0px", width:"380px"}}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>To Account:</Form.Label>
          <Form.Control
            type="text"
            value={toAccount}
            onChange={(e) => setToAccount(e.target.value)}
            style={{width:"380px"}}
          />
        </Form.Group>
        </div>
        {/* <Form.Group>
          <Form.Check
            type="checkbox"
            label="Sort by Date"
            checked={sortByDate}
            onChange={(e) => setSortByDate(e.target.checked)}
          />
        </Form.Group> */}
        <Button variant="primary" onClick={filterTransactions} style={{marginTop:"10px"}}>
          Submit
        </Button>
      </Form>

      {/* Transaction table */}
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Date</th>
            <th>From Account</th>
            <th>To Account</th>
            <th>Description</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction, index) => (
            <tr key={index}>
              <td>{transaction.date}</td>
              <td>{transaction.from}</td>
              <td>{transaction.to}</td>
              <td>{transaction.description}</td>
              <td>{transaction.amount.toFixed(2)}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default AccountStatement;
