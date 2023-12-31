import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Context } from "../context/Context";
import UserService from "../services/UserService";
import BeneficiaryService from "../services/BeneficiaryService";

function AddBeneficiary() {
  const navigate = useNavigate();

  const { userId, dispatch } = useContext(Context);

  const [kyc, setKyc] = useState(false);
  const [beneficiaryName, setBeneficiaryName] = useState("");
  const [accountNumber, setAccountNumber] = useState("");
  const [confirmAccountNumber, setConfirmAccountNumber] = useState("");
  const [ifscCode, setIfscCode] = useState("");
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  useEffect(() => {
    UserService.getUserDetails(userId).then((response) => {
      setKyc(response.kyc);
    });
  });

  const createBeneficiary = async () => {
    var beneficiaryData = {
        ifscCode: ifscCode,
        accountNo: accountNumber,
        name: beneficiaryName,
    }
    BeneficiaryService.addBeneficiary(userId, beneficiaryData).then((response) => {
        if (response.status == 200 || response.status == 201) {
            setError(false);
            setSuccessMessage("Added Beneficiary successful");
            setTimeout(() => {
              navigate("/dashboard/view-beneficiary");
            }, 1000);
        }
        else{
            setError(response.response.data);
        }
    })

    // try {
    //   const response = await axios.post(
    //     `http://localhost:8085/beneficiaries/${userId}`,
    //     {
    //       ifscCode: ifscCode,
    //       accountNo: accountNumber,
    //       name: beneficiaryName,
    //     }
    //   );
    //   console.log(response);
    //   if (response.status == 200 || response.status == 201) {
    //     setError(false);
    //     setSuccessMessage("Added Beneficiary successful");
    //     setTimeout(() => {
    //       navigate("/dashboard/view-beneficiary");
    //     }, 1000);
    //   }
    // } catch (error) {
    //   setError(error.response.data);
    // }
  };

  const handleAddBeneficiary = (e) => {
    e.preventDefault();
    if (accountNumber === confirmAccountNumber) {
      createBeneficiary();
    } else {
      setError("Account numbers do not match.");
    }
  };

  return (
    <div className="form-bg my-5 mx-auto">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
            <div className="form-container">
              {kyc ? (
                <h3 className="title">Add Beneficiary</h3>
              ) : (
                <h3 className="title" style={{ color: "red" }}>
                  Contact Admin for KYC verification
                </h3>
              )}
              {kyc && (
                <form className="form-vertical" onSubmit={handleAddBeneficiary}>
                  <div className="form-group">
                    {successMessage && (
                      <div className="alert alert-success">
                        <label>{successMessage}</label>
                      </div>
                    )}
                    {error && (
                      <div className="alert alert-danger">
                        <label>{error}</label>
                      </div>
                    )}
                    <label>Beneficiary Name*</label>
                    <input
                      className="form-control"
                      type="text"
                      value={beneficiaryName}
                      onChange={(e) => setBeneficiaryName(e.target.value)}
                    />
                  </div>
                  <div className="form-group">
                    <label>Account Number*</label>
                    <input
                      className="form-control"
                      type="text"
                      value={accountNumber}
                      onChange={(e) => setAccountNumber(e.target.value)}
                    />
                  </div>
                  <div className="form-group">
                    <label>Confirm Account Number*</label>
                    <input
                      className="form-control"
                      type="text"
                      value={confirmAccountNumber}
                      onChange={(e) => setConfirmAccountNumber(e.target.value)}
                    />
                  </div>
                  <div className="form-group">
                    <label>IFSC Code*</label>
                    <input
                      className="form-control"
                      type="text"
                      value={ifscCode}
                      onChange={(e) => setIfscCode(e.target.value)}
                    />
                  </div>
                  <div className="text-center mb-4">
                    <button
                      className="my-4 mx-auto btn btn-success"
                      type="submit"
                    >
                      Add Beneficiary
                    </button>
                  </div>
                </form>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AddBeneficiary;
