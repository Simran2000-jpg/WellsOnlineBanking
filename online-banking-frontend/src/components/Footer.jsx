import React, { useContext } from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import "../styles/Footer.css";
import { Context } from "../context/Context";

const Footer = () => {
  const { userId, dispatch } = useContext(Context);
  return (
    <>
      <Navbar bg={userId === "admin" ? "dark" : "light"} className="footer">
        <Container>
          <Nav>
            <Nav.Link
              className={userId === "admin" ? "text-white" : "text-dark"}
            >
              All &copy; Rights reserved to Nexus Bank
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
};

export default Footer;
