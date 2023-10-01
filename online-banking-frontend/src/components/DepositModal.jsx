import React, { useEffect, useState } from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

const DepositModal = ({ show, onHide, onDeposit }) => {
  const [amount, setAmount] = useState("");
  useEffect(() => {
    setAmount("");
  }, [onHide]);

  const handleDeposit = () => {
    // Validate and perform the deposit action
    if (parseFloat(amount) > 0) {
      onDeposit(parseFloat(amount));
      onHide();
    }
  };

  return (
    <Modal show={show} onHide={onHide}>
      <Modal.Header closeButton>
        <Modal.Title>Deposit Money</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className="form-group">
          <label>Amount to Deposit:</label>
          <input
            type="number"
            className="form-control"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
          />
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onHide}>
          Cancel
        </Button>
        <Button variant="primary" onClick={handleDeposit}>
          Deposit
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default DepositModal;
