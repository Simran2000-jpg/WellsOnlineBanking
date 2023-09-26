import React, { useState } from "react";
import { NavLink, useLocation } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css"; // Import Bootstrap CSS
import "../styles/Register.css";
import "../styles/SidebarComponent.css"; // Create a new CSS file for the sidebar styles

const SidebarComponent = () => {
  const location = useLocation();
  const pathname = location.pathname.split("/");
  const path = pathname[1];

  return (
    <div className="sidebar bg-light">
      <ul>
        <li>
          <NavLink to={`/${path}/account-details`}>
            <i className="bi bi-person"></i> Account Details
          </NavLink>
        </li>
        <li>
          <NavLink to={`/${path}/add-beneficiary`}>
            <i className="bi bi-card-list"></i> Add Beneficiary
          </NavLink>
        </li>
        <li>
          <NavLink to={`/${path}/account-statement`}>
            <i className="bi bi-file-text"></i> Account Statement
          </NavLink>
        </li>
        <li>
          <NavLink to={`/${path}/transaction`}>
            <i className="bi bi-currency-exchange"></i> Funds Transfer
          </NavLink>
        </li>
      </ul>
    </div>
  );
};

export default SidebarComponent;
