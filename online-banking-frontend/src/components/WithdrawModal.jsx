import React, { useEffect, useState } from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

const WithdrawModal = ({ show, account, onHide, onWithdraw }) => {
  const [amount, setAmount] = useState("");
  useEffect(() => {
    setAmount("");
  }, [onHide]);

  const handleWithdraw = () => {
    // Validate and perform the withdrawal action
    if (parseFloat(amount) > 0) {
      onWithdraw(account.accountNo, " ", parseFloat(amount));
      onHide();
    }
  };

  return (
    <Modal show={show} onHide={onHide}>
      <Modal.Header closeButton>
        <Modal.Title>Withdraw Money</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className="form-group">
          <label>Account Number: </label>
          <input
            type="text"
            className="form-control"
            value={account.accountNo}
            readOnly
            disabled
          />

          <label>Amount to Withdraw:</label>
          <input
            type="number"
            className="form-control"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
          />

          <label>Remarks:</label>
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onHide}>
          Cancel
        </Button>
        <Button variant="danger" onClick={handleWithdraw}>
          Withdraw
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default WithdrawModal;
