import React, { useEffect, useState } from "react";
import { Container, Image } from "react-bootstrap";
import "../styles/Home.css";

const Home = () => {
  const [user, setUser] = useState("");

  useEffect(() => {
    setUser(localStorage.getItem("phoneNumber"));
  }, []);

  return (
    // <div className='landing-page-img'>
    // </div>

    <Container className="pt-2">
      <h1 className="display-5">Nexus Bank</h1>
      {user === null ? <div>Not Logged in</div> : <div>Logged in</div>}
    </Container>
  );
};

export default Home;
