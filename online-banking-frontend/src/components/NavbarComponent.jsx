import React, { useEffect, useState } from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { NavLink, useNavigate } from "react-router-dom";
import Logo from "../logo.svg";

import "../styles/NavbarComponent.css";

const NavbarComponent = () => {
  const history = useNavigate();

  const [user, setUser] = useState("");

  useEffect(() => {
    const onStorage = () => {
      setUser(localStorage.getItem("userId"));
    };
    window.addEventListener("storage", onStorage);

    return () => {
      window.removeEventListener("storage", onStorage);
    };
  });

  const handleLogout = async (e) => {
    e.preventDefault();

    localStorage.removeItem("userId");
    window.dispatchEvent(new Event("storage"));
    history("/");
  };

  return (
    <>
      <Navbar fixed="top" bg="light">
        <Container>
          <NavLink
            to={"/"}
            className={"text-decoration-none"}
            style={{ marginLeft: "-85px" }}
          >
            <Navbar.Brand>
              <img alt="" src={Logo} width="40" height="30" />
              Nexus Bank
            </Navbar.Brand>
          </NavLink>
          <Nav className="justify-content-end">
            {user === null && (
              <Nav.Item className="my-2 mx-2 nav-item-styling">
                <NavLink
                  to={"/openaccount"}
                  className="text-decoration-none nav-link-styling"
                >
                  <i className="bi bi-bank"></i>
                  <span className="mx-2">Open an Account</span>
                </NavLink>
              </Nav.Item>
            )}
            {user !== null && (
              <Nav.Item className="my-2 mx-2 nav-item-styling">
                <NavLink
                  to={"/register"}
                  className="text-decoration-none nav-link-styling"
                >
                  <i className="bi bi-person-fill-add"></i>
                  <span className="mx-2">Register Internet Banking</span>
                </NavLink>
              </Nav.Item>
            )}
            {user !== null && (
              <Nav.Item className="my-2 mx-2 nav-item-styling">
                <NavLink
                  to={"/dashboard/account-details"}
                  className="text-decoration-none nav-link-styling"
                >
                  <i className="bi bi-speedometer2"></i>
                  <span className="mx-2">Dashboard</span>
                </NavLink>
              </Nav.Item>
            )}
            {user === null ? (
              <Nav.Item className="my-2 mx-2 nav-item-styling">
                <NavLink
                  to={"/login"}
                  className="text-decoration-none nav-link-styling"
                >
                  <i className="bi bi-person-fill"></i>
                  <span className="mx-2">Login</span>
                </NavLink>
              </Nav.Item>
            ) : (
              <Nav.Item className="my-2 mx-2 nav-item-styling">
                <NavLink
                  className="text-decoration-none nav-link-styling"
                  onClick={handleLogout}
                >
                  <i className="bi bi-box-arrow-right"></i>
                  <span className="mx-2">Logout</span>
                </NavLink>
              </Nav.Item>
            )}
          </Nav>
        </Container>
      </Navbar>
    </>
  );
};

export default NavbarComponent;
