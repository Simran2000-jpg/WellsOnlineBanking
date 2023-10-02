import React, { useContext } from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import "../styles/Footer.css";
import { Context } from "../context/Context";

const Footer = () => {
  const { userId, dispatch } = useContext(Context);
  let year = new Date().getFullYear();
  return (
    <>
      <Navbar bg={userId === "admin" ? "dark" : "light"} className="footer">
        <Container>
          <Nav>
            <Nav.Link
              className={userId === "admin" ? "text-white" : "text-dark"}
            >
              All &copy; Rights reserved to Nexus Bank, {year}.
            </Nav.Link>
          </Nav>

          {userId !== "admin" && (
            <Nav className="justify-content-end">
              <Nav.Link href="mailto:admin@nexusbank.org">
                For queries and complaints, contact us at admin@nexusbank.org
              </Nav.Link>
            </Nav>
          )}
        </Container>
      </Navbar>
    </>
  );
};

export default Footer;
