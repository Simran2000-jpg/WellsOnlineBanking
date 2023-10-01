import React, { useContext, useState } from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import "../styles/AddAccount.css";
import axios from "axios";
import { Context } from "../context/Context";
import { useNavigate } from "react-router-dom";

const AddAccount = () => {
  const navigate = useNavigate();
  const { userId, dispatch } = useContext(Context);

  const [accountType, setAccountType] = useState("");
  const [transactionPassword, setTransactionPassword] = useState("");
  const [confirmTransactionPassword, setConfirmTransactionPassword] =
    useState("");
  const [address, setAddress] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [pincode, setPincode] = useState(0);
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (transactionPassword !== confirmTransactionPassword) {
      alert("Transaction passwords do not match.");
      return;
    }

    console.log("Account Type:", accountType);
    console.log("Transaction Password:", transactionPassword);
    console.log("Address:", address);
    console.log("City:", city);
    console.log("State:", state);
    console.log("Pincode:", pincode);

    axios
      .post(
        "http://localhost:8085/createAnotherNewAccount/" + userId,
        { address, city, state, pincode },
        { params: { accountType, transactionPassword } }
      )
      .then((res, err) => {
        setSuccess(true);
        setSuccessMessage("Created New Account successfully");
        setTimeout(() => {
          navigate("/");
        }, 1000);
      })
      .catch((err) => {
        setError("Error occured in creating account");
        console.log(err);
      });
  };

  return (
    <div className="add-account-form">
      <h2 className="form-title">Create New Account</h2>
      <Form onSubmit={handleSubmit}>
        {success && (
          <div className="alert alert-success">
            <label>{successMessage}</label>
          </div>
        )}
        <Form.Group controlId="accountType" className="form-group">
          <Form.Label>Account Type</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter account type"
            value={accountType}
            onChange={(e) => setAccountType(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group controlId="transactionPassword" className="form-group">
          <Form.Label>Transaction Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Enter transaction password"
            value={transactionPassword}
            onChange={(e) => setTransactionPassword(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group
          controlId="confirmTransactionPassword"
          className="form-group"
        >
          <Form.Label>Confirm Transaction Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Confirm transaction password"
            value={confirmTransactionPassword}
            onChange={(e) => setConfirmTransactionPassword(e.target.value)}
            required
          />
        </Form.Group>

        <h3 className="address-title">Mailing Address</h3>
        <Row>
          <Col>
            <Form.Group controlId="address" className="form-group">
              <Form.Label>Address</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter address"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                required
              />
            </Form.Group>
          </Col>
          <Col>
            <Form.Group controlId="city" className="form-group">
              <Form.Label>City</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter city"
                value={city}
                onChange={(e) => setCity(e.target.value)}
                required
              />
            </Form.Group>
          </Col>
        </Row>
        <Row>
          <Col>
            <Form.Group controlId="state" className="form-group">
              <Form.Label>State</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter state"
                value={state}
                onChange={(e) => setState(e.target.value)}
                required
              />
            </Form.Group>
          </Col>
          <Col>
            <Form.Group controlId="pincode" className="form-group">
              <Form.Label>Pincode</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter pincode"
                value={pincode}
                onChange={(e) => setPincode(e.target.value)}
                required
              />
            </Form.Group>
          </Col>
        </Row>

        <Button variant="primary" type="submit" style={{ marginTop: "25px" }}>
          Add Account
        </Button>
      </Form>
    </div>
  );
};

export default AddAccount;
