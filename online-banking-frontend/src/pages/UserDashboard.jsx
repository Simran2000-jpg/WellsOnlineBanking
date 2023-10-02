import React from "react";
import { Container } from "react-bootstrap";
import "../styles/Home.css";
import SidebarComponent from "../components/SidebarComponent";
import AccountDetails from "../components/AccountDetails";
import AccountStatement from "../components/AccountStatement";
import { useLocation } from "react-router-dom";
import Transaction from "./Transaction";
import AddBeneficiary from "./AddBeneficiary";
import ViewBeneficiary from "./ViewBeneficiary";
import Register from "../pages/Register";

const UserDasboard = () => {
  const location = useLocation();

  const path = location.pathname.split("/");

  return (
    <Container className="pt-2">
      <SidebarComponent />
      {path[2] === "account-details" ? (
        <AccountDetails />
      ) : path[2] === "account-statement" ? (
        <AccountStatement />
      ) : path[2] === "funds-transfer" ? (
        <Transaction />
      ) : path[2] === "add-beneficiary" ? (
        <AddBeneficiary />
      ) : path[2] === "view-beneficiary" ? (
        <ViewBeneficiary />
      ) : path[2] === "internet-banking" && path[3] ? (
        <Register />
      ) : (
        ""
      )}
    </Container>
  );
};

export default UserDasboard;
