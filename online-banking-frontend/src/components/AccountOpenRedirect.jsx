import React, { useState } from "react";
import { CopyToClipboard } from "react-copy-to-clipboard";

const AccountOpenRedirect = (props) => {
  const generatedPassword = props.generatedPassword;
  const [isCopied, setIsCopied] = useState(false);

  const onCopyText = () => {
    setIsCopied(true);
    setTimeout(() => {
      setIsCopied(false);
    }, 1000);
  };

  return (
    <>
      <div
        style={{
          display: "flex",
          color: "green",
          justifyContent: "flex-start",
        }}
      >
        <i>Thanks for choosing the Nexus Bank!</i>
      </div>
      <div
        style={{
          display: "flex",
          justifyContent: "flex-start",
          fontSize: "15px",
          marginTop: "20px",
        }}
      >
        Use your &nbsp;
        <i>
          <b>Phone Number</b>
        </i>
        &nbsp; and the &nbsp;
        <i>
          <b>Temporary Password</b>
        </i>
        &nbsp; given below to &nbsp;<b>Login </b>&nbsp; temporarily.
      </div>
      <div
        style={{
          display: "flex",
          justifyContent: "flex-start",
          fontSize: "25px",
          fontFamily: "sans-serif",
          marginTop: "30px"
        }}
      >
        Temporary Password :{" "}
        <span
          style={{
            color: isCopied ? "green" : "black",
          }}
        >
          {generatedPassword}
        </span>
        <CopyToClipboard text={generatedPassword} onCopy={onCopyText}>
          <div className="copy-area">
            &nbsp;
            {!isCopied ? (
              <b>
                <i
                  class="bi bi-clipboard"
                  style={{ fontSize: "15px", color: "grey", cursor: "pointer" }}
                >
                  Copy to Clipboard
                </i>
              </b>
            ) : (
              <span
                className={`copy-feedback ${isCopied ? "active" : ""}`}
                style={{ fontSize: "15px", color: "green" }}
              >
                <i>Copied!</i>
              </span>
            )}
          </div>
        </CopyToClipboard>
      </div>
    </>
  );
};

export default AccountOpenRedirect;
