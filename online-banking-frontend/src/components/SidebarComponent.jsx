import React, { useState } from "react";
import { NavLink, useLocation } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import "../styles/Register.css";
import "../styles/SidebarComponent.css"; 

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
          <NavLink to={`/${path}/view-beneficiary`}>
            <i className="bi bi-card-list"></i> Manage Beneficiary
          </NavLink>
        </li>
        <li>
          <NavLink to={`/${path}/account-statement`}>
            <i className="bi bi-file-text"></i> Account Statement
          </NavLink>
        </li>
        <li>
          <NavLink to={`/${path}/funds-transfer`}>
            <i className="bi bi-currency-exchange"></i> Funds Transfer
          </NavLink>
        </li>
        <li>
          <NavLink to={`/${path}/internet-banking`}>
          <i className="bi bi-person-fill-add"></i> Internet Banking
          </NavLink>
        </li>
      </ul>
    </div>
  );
};

export default SidebarComponent;
