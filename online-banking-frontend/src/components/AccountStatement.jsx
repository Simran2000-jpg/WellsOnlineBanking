import React, { useState, useEffect } from "react";
import { Button, Form, Table } from "react-bootstrap";
import "../styles/AccountStatement.css";
import axios from "axios";

const AccountStatement = () => {
  const [user, setUser] = useState(null);
  const [accounts, setAccounts] = useState([]);
  const [selectedAccount, setSelectedAccount] = useState("");
  const [beneficiaries, setBeneficiaries] = useState([]);
  const [transactions, setTransactions] = useState([]);
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const [fromAccount, setFromAccount] = useState("");
  const [toAccount, setToAccount] = useState("");

  useEffect(() => {
    setUser(localStorage.getItem("userId"));
    if (user) {
      fetchTransactionsByAccountsByUser();
    }
  }, [user]);

  const fetchTransactionsByAccountsByUser = async () => {
    console.log("user transaction : ", user);

    const response = await axios.get(
      "http://localhost:8085/transactions/accounts/user/" + user
    );
    console.log("transactions", response.data);
    setTransactions(response.data);
  };

  const fetchAccounts = async () => {
    console.log("user : ", user);
    const response = await axios.get(
      " http://localhost:8085/accounts/user/" + user
    );
    console.log("account", response.data);
    setAccounts(response.data);
  };

  const fetchTransactions = async () => {
    console.log("BHEEM");
    return axios
      .get(`http://localhost:8085/transactions/accounts/${selectedAccount}`)
      .then((res) => {
        setTransactions(res.data);
        console.log("done bheeeem", transactions)
      });
  };

  const fetchBeneficiaries = async () => {
    axios
      .get("http://localhost:8085/beneficiaries/" + user)
      .then((response) => {
        console.log(response);
        setBeneficiaries(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data: ", error);
      });
  };

  const filterTransactions = async () => {
    fetchTransactions().then(() => {
      console.log("toAccount", toAccount);
      console.log("all", transactions);
      if (toAccount) {
        let filteredTransactions = transactions.filter(
          (transaction) =>
            transaction.toAccount.accountNo.toString() === toAccount
        );
        console.log("filtered", filteredTransactions);
        setTransactions([...filteredTransactions]);
        console.log("done", transactions);
      }
    });
    // if (startDate && endDate) {
    //   filteredTransactions = filteredTransactions.filter(
    //     (transaction) =>
    //       transaction.transactionDateTime >= startDate &&
    //       transaction.date <= endDate
    //   );
    // }
  };

  return (
    <div className="account-statement-container">
      <h2 className="account-statement-title">Account Statement</h2>

      {/* Account selection */}
      <Form>
        <Form.Group>
          <Form.Label>From Account:</Form.Label>
          <Form.Control
            as="select"
            onClick={fetchAccounts}
            onChange={(e) => setSelectedAccount(e.target.value)}
          >
            <option value="">Select From Account</option>
            {accounts.length > 0 &&
              accounts.map((account, index) => (
                <option key={index} value={account.accountNo}>
                  {account.accountNo}
                </option>
              ))}
          </Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>To Account:</Form.Label>
          <Form.Control
            as="select"
            onClick={fetchBeneficiaries}
            onChange={(e) => setToAccount(e.target.value)}
          >
            <option value="">Select To Account</option>
            {beneficiaries.length > 0 &&
              beneficiaries.map((beneficiary, index) => (
                <option key={index} value={beneficiary.accountNo}>
                  {beneficiary.accountNo}
                </option>
              ))}
          </Form.Control>
        </Form.Group>
      </Form>

      {/* Filter and sort options */}
      <Form>
        <div style={{ display: "inline-flex", width: "100%" }}>
          <Form.Group>
            <Form.Label>Start Date:</Form.Label>
            <Form.Control
              type="date"
              value={startDate}
              onChange={(e) => setStartDate(e.target.value)}
              style={{ marginLeft: "0px", width: "380px" }}
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>End Date:</Form.Label>
            <Form.Control
              type="date"
              value={endDate}
              onChange={(e) => setEndDate(e.target.value)}
              style={{ width: "380px" }}
            />
          </Form.Group>
        </div>
        <Button
          variant="primary"
          onClick={filterTransactions}
          style={{ marginTop: "10px" }}
        >
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
              <td>{transaction.transactionDateTime}</td>
              <td>{transaction.fromAccount.accountNo}</td>
              <td>{transaction.toAccount.accountNo}</td>
              <td>{transaction.remarks}</td>
              <td>{transaction.amount}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default AccountStatement;

// useEffect(() => {
//   setUser(localStorage.getItem("userId"));
//   if (user) {
//     fetchAccounts();
//     fetchTransactions();
//   }
// }, [user, accounts]);

// const fetchTransactions = async () => {
//   console.log("user transaction : ", user);
//   if (accounts && accounts[0].accountNo) {
//     console.log("accountNumber transaction", accounts[0].accountNo);
//     const acc = accounts[0].accountNo;
//     const response = await axios.get(
//       `http://localhost:8085/transactions/accounts/${acc}`
//     );
//     console.log("transactions", response.data);
//     setTransactions(response.data);
//   }
// };
