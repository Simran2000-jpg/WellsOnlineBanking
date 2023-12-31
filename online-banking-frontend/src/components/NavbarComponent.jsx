import React, { useContext, useEffect, useState } from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { NavLink, useNavigate } from "react-router-dom";
// import Logo from "../logo.svg";
import Logo from "../nexus_bank_logo.png";

import "../styles/NavbarComponent.css";
import { Context } from "../context/Context";
import { toast } from "react-toastify";

const NavbarComponent = () => {
  const history = useNavigate();

  const { userId, dispatch } = useContext(Context);

  const handleLogout = () => {
    dispatch({
      type: "LOGOUT",
    });
    window.location.href = "/";
  };

  return (
    <>
      <Navbar
        className={
          userId === "admin" ? "navbar-styling-admin bg-dark" : "navbar-styling"
        }
        fixed="top"
        bg="light"
      >
        <Container>
          <NavLink
            to={"/"}
            className={"text-decoration-none nav-item-styling"}
            style={{ marginLeft: "-85px" }}
          >
            <Navbar.Brand>
              <img alt="" src={Logo} width="40" height="30" />
              <span className="nav-link-styling m-3 text-bold text-dark">
                Nexus Bank
              </span>
            </Navbar.Brand>
          </NavLink>
          <Nav className="justify-content-end">
            {userId === null && (
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
            {userId && userId !== "admin" && (
              <Nav.Item className="my-2 mx-2 nav-item-styling">
                <NavLink
                  to={"/create-account"}
                  className="text-decoration-none nav-link-styling"
                >
                  <i className="bi bi-person"></i>
                  <span className="mx-2">Create New Account</span>
                </NavLink>
              </Nav.Item>
            )}
            {userId !== null && userId !== "admin" && (
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
            {userId !== null && userId === "admin" && (
              <Nav.Item className="my-2 mx-2 nav-item-styling">
                <NavLink
                  to={"/admin"}
                  className="text-decoration-none nav-link-styling"
                >
                  <i className="bi bi-speedometer2"></i>
                  <span className="mx-2">Admin Dashboard</span>
                </NavLink>
              </Nav.Item>
            )}
            {userId === null ? (
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
