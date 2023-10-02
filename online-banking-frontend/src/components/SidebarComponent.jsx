import React, { useContext, useEffect, useState } from "react";
import { NavLink, useLocation } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import "../styles/Register.css";
import "../styles/SidebarComponent.css";
import AccountService from "../services/AccountService";
import { Context } from "../context/Context";
import UserService from "../services/UserService";

const SidebarComponent = () => {
  const location = useLocation();
  const path = location.pathname.split("/");

  const { userId, dispatch } = useContext(Context);

  const [kyc, setKyc] = useState(false);
  const [selectedAccount, setSelectedAccount] = useState({
    accountNo: 0,
    balance: 0,
    ifscCode: "",
    transactionPassword: null,
    isActive: false,
  });

  const fetchData = () => {
    UserService.getUserDetails(userId).then((response) => {
      setKyc(response.kyc);
    });
    AccountService.getAccountDetails(userId).then((response) => {
      if (response.length == 0) {
        console.log("No data found");
        return;
      } else {
        if (path[3]) {
          response.map((account) =>
            account.accountNo == path[3] ? setSelectedAccount(account) : null
          );
        }
      }
    });
  };
  useEffect(() => {
    fetchData();
  }, [selectedAccount]);

  return (
    <div className="sidebar bg-light">
      <ul>
        <li>
          <NavLink to={`/${path[1]}/account-details`}>
            <i className="bi bi-person"></i> Account Details
          </NavLink>
        </li>
        {kyc && (
          <li>
            <NavLink to={`/${path[1]}/view-beneficiary`}>
              <i className="bi bi-card-list"></i> Manage Beneficiary
            </NavLink>
          </li>
        )}
        {kyc && (
          <li>
            <NavLink to={`/${path[1]}/account-statement`}>
              <i className="bi bi-file-text"></i> Account Statement
            </NavLink>
          </li>
        )}
        {kyc && (
          <li>
            <NavLink to={`/${path[1]}/funds-transfer`}>
              <i className="bi bi-currency-exchange"></i> Funds Transfer
            </NavLink>
          </li>
        )}
        {kyc && path[3] && !selectedAccount.transactionPassword && (
          <li>
            <NavLink to={`/${path[1]}/internet-banking`}>
              <i className="bi bi-person-fill-add"></i> Internet banking
            </NavLink>
          </li>
        )}
        {kyc && (
          <li>
            <NavLink to={`/${path[1]}/update-transactionPassword`}>
              <i className="bi bi-key"></i> Transaction Password
            </NavLink>
          </li>
        )}
        <li>
          <NavLink to={`/${path[1]}/update-password`}>
            <i className="bi bi-lock"></i> Update Login Password
          </NavLink>
        </li>
      </ul>
    </div>
  );
};

export default SidebarComponent;
