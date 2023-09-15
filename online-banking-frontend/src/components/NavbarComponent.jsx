import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { NavLink } from "react-router-dom";
import Logo from "../logo.svg";

import "../styles/NavbarComponent.css";

const NavbarComponent = () => {
  return (
    <>
      <Navbar fixed="top" bg="light">
        <Container>
          <NavLink to={"/"} className={'text-decoration-none'}>
            <Navbar.Brand>
              <img alt="" src={Logo} width="40" height="30" className="d-inline-block align-top" />
              Nexus Bank
            </Navbar.Brand>
          </NavLink>
          <Nav className="justify-content-end">
            <Nav.Item className="my-2 mx-2 nav-item-styling">
              <NavLink to={"/"} className="text-decoration-none nav-link-styling">
                <i className="bi bi-bank"></i>
                <span className="mx-2">Open an Account</span>
              </NavLink>
            </Nav.Item>
            <Nav.Item className="my-2 mx-2 nav-item-styling">
              <NavLink to={"/login"} className="text-decoration-none nav-link-styling">
                <i className="bi bi-person-fill"></i>
                <span className="mx-2">Login</span>
              </NavLink>
            </Nav.Item>
            <Nav.Item className="my-2 mx-2 nav-item-styling">
              <NavLink to={"/register"} className="text-decoration-none nav-link-styling">
                <i className="bi bi-person-fill-add"></i>
                <span className="mx-2">Register</span>
              </NavLink>
            </Nav.Item>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
};

export default NavbarComponent;
