import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { NavLink } from "react-router-dom";
import Logo from "../logo.svg";

import "../styles/NavbarComponent.css";

const NavbarComponent = () => {
  return (
    <>
      <Navbar fixed="top" bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="#home">
            <img alt="" src={Logo} width="30" height="30" className="d-inline-block align-top" />
            Nexus Bank
          </Navbar.Brand>
          <Nav className="justify-content-end">
            <Nav.Link href="#home">
              <i className="bi bi-bank mr-2"></i>
              <span className="mx-2">Open an Account</span>
            </Nav.Link>
            <NavLink to={"/login"} className="nav-link-styling">
              <Nav.Link>
                <i className="bi bi-person-fill mr-2"></i>
                <span className="mx-2">Login</span>
              </Nav.Link>
            </NavLink>
            <Nav.Link>
              <NavLink to={"/register"} className="nav-link-styling">
                <i className="bi bi-person-fill-add mr-2"></i>
                <span className="mx-2">Register</span>
              </NavLink>
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
};

export default NavbarComponent;
