import React, { useContext } from "react";
import { Link } from "react-router-dom";
import "../styles/Home.css";
import { Context } from "../context/Context";
import BankAccounts from "../components/BankAccounts";

const Test = () => {
  const { userId, dispatch } = useContext(Context);

  return (
    <div className="home-container">
      <header className="header">
        <div className="header-content">
          {userId === null ? (
            <>
              <h1>Welcome to Nexus Bank</h1>
              <p>Your Trusted Banking Partner</p>
              <Link to="/openaccount" className="btn btn-primary btn-lg">
                Open an Account
              </Link>
            </>
          ) : (
            <>
              <h1>Thanks for choosing Nexus Bank!</h1>
              <BankAccounts />
            </>
          )}
        </div>
      </header>

      <section className="features">
        <div className="feature">
          <i className="bi bi-bank"></i>
          <h2>Secure Banking</h2>
          <p>Experience top-notch security for your transactions.</p>
        </div>

        <div className="feature">
          <i className="bi bi-credit-card"></i>
          <h2>Easy Payments</h2>
          <p>Make payments and transfers with ease.</p>
        </div>

        <div className="feature">
          <i className="bi bi-person"></i>
          <h2>Personalized Service</h2>
          <p>Get personalized banking solutions tailored to your needs.</p>
        </div>
      </section>

      {userId === null && (
        <section className="cta">
          <div className="cta-content">
            <h2>Ready to get started?</h2>
            <p>Open your account today and experience modern banking.</p>
            <Link to="/openaccount" className="btn btn-primary btn-lg">
              Open an Account
            </Link>
          </div>
        </section>
      )}
    </div>
  );
};

export default Test;
