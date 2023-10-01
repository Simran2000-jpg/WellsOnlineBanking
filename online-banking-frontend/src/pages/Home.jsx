import React, { useContext, useEffect, useState } from "react";
import { Container, Image, NavLink } from "react-bootstrap";
import "../styles/Home.css";
import { Context } from "../context/Context";
import BankAccounts from "../components/BankAccounts";
import Login from "./Login";

const Home = () => {
  const { userId, dispatch } = useContext(Context);

  return (
    <Container
      className="pt-2 landing-page-img"
    >
      <h1 className="display-15" style={{ marginTop: "20px" }}>
        Thanks for choosing Nexus Bank :)
      </h1>
      {userId && <BankAccounts />}
    </Container>
  );
};

export default Home;
