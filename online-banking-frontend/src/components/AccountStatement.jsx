import React, { useState, useEffect } from "react";
import { Button, Form, Table } from "react-bootstrap";
import moment from "moment";
import "../styles/AccountStatement.css";
import axios from "axios";

const AccountStatement = () => {
  const [user, setUser] = useState(null);
  const [accounts, setAccounts] = useState([]);
  const [beneficiaries, setBeneficiaries] = useState([]);
  const [transactions, setTransactions] = useState([]);
  const [filteredTransactions, setFilteredTransactions] = useState([]);
  const [filteredToAccountTransactions, setFilteredToAccountTransactions] =
    useState([]);
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);

  useEffect(() => {
    setUser(localStorage.getItem("userId"));
    if (user) {
      fetchTransactionsByAccountsByUser();
    }
  }, [user]);

  const fetchTransactionsByAccountsByUser = async () => {
    const response = await axios.get(
      "http://localhost:8085/transactions/accounts/user/" + user
    );
    setFilteredTransactions(response.data);
    setFilteredToAccountTransactions(response.data);
    setTransactions(response.data);
  };

  const fetchAccounts = async () => {
    const response = await axios.get(
      " http://localhost:8085/accounts/user/" + user
    );
    setAccounts(response.data);
  };

  const fetchTransactions = async (e) => {
    if (e.target.value === "All From Accounts") {
      return axios
        .get("http://localhost:8085/transactions/accounts/user/" + user)
        .then((res) => {
          setTransactions(res.data);
        });
    } else {
      return axios
        .get(`http://localhost:8085/transactions/accounts/${e.target.value}`)
        .then((res) => {
          setTransactions(res.data);
        });
    }
  };

  const fetchBeneficiaries = async () => {
    axios
      .get("http://localhost:8085/beneficiaries/" + user)
      .then((response) => {
        setBeneficiaries(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data: ", error);
      });
  };

  const filterToAccount = async (e) => {
    console.log("toAccount : ", e.target.value);
    console.log("transactions : ", transactions);
    console.log("filtered transactions : ", filteredTransactions);
    if (e.target.value === "All To Accounts") {
      setFilteredToAccountTransactions([...transactions]);
    } else {
      let tempTransactions = transactions.filter(
        (transaction) =>
          transaction.toAccount.accountNo.toString() === e.target.value
      );
      console.log("temp transactions : ", tempTransactions);
      setFilteredToAccountTransactions([...tempTransactions]);
    }
  };

  const filterDate = async () => {
    if (startDate && endDate) {
      let tempTransactions = filteredToAccountTransactions.filter(
        (transaction) =>
          moment(transaction.transactionDateTime).format("YYYY-MM-DD") >=
            startDate &&
          moment(transaction.transactionDateTime).format("YYYY-MM-DD") <=
            endDate
      );
      setFilteredTransactions([...tempTransactions]);
    } else {
      setFilteredTransactions([...filteredToAccountTransactions]);
    }
  };

  const filterTransactions = async () => {
    console.log("before transactions : ", transactions);
    console.log(
      "before filtered transactions : ",
      filteredToAccountTransactions
    );

    filterDate();
    console.log("after transactions : ", transactions);
    console.log("after filtered transactions : ", filteredTransactions);
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
            onChange={fetchTransactions}
          >
            <option value="All From Accounts">All From Accounts</option>
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
            onChange={filterToAccount}
          >
            <option value="All To Accounts">All To Accounts</option>
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
          {filteredTransactions.map((transaction, index) => (
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
