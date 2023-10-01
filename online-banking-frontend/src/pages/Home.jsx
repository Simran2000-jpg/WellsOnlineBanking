import React, { useContext, useEffect, useState } from "react";
import { Container, Image } from "react-bootstrap";
import "../styles/Home.css";
import { Context } from "../context/Context";
import BankAccounts from "../components/BankAccounts";

const Home = () => {
  const {userId, dispatch} = useContext(Context);

  return (
    // <div className='landing-page-img'>
    // </div>

    <Container className="pt-2">
      <h1 className="display-15" style={{marginTop: "20px"}}>Thanks for choosing Nexus Bank :)</h1>
      {userId ?  <BankAccounts/> : <div>Not Logged in</div>}
    </Container>
  );
};

export default Home;
