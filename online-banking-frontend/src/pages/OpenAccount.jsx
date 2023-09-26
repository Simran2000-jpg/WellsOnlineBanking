import React, { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { NavLink, useNavigate } from "react-router-dom";
import PersonalDetailsForm from "../components/PersonalDetailsForm";
import AddressDetailsForm from "../components/AddressDetailsForm";
import OccupationDetailsForm from "../components/OccupationDetailsForm";
import axios from "axios";
import AccountOpenRedirect from "../components/AccountOpenRedirect";

const OpenAccount = () => {
  const history = useNavigate();

  const FINAL_STEP_NUMBER = 3;
  const [stepNumber, setStepNumber] = useState(1);

  const [success, setSuccess] = useState(false);
  const [generatedPassword, setGeneratedPassword] = useState("");

  const user = {
    firstName: "",
    middleName: "",
    lastName: "",
    gender: "",
    fatherName: "",
    phoneNumber: "",
    emailId: "",
    panNumber: "",
    aadharNumber: "",
    dob: "",
    occupation: "",
    sourceOfIncome: "",
    grossAnnualIncome: "",
    loginPassword: "",
    kyc: false,
    residentialAddress: {
      address: "",
      city: "",
      state: "",
      pincode: "",
    },
    permanentAddress: {
      address: "",
      city: "",
      state: "",
      pincode: "",
    },
  };

  const [formFieldValues, setFormFieldValues] = useState(user);

  const handleFormSubmit = async () => {
    generatePassword();
    setFormFieldValues((prevState) => ({
      ...prevState,
      loginPassword: generatedPassword,
    }));

    await axios.post("http://localhost:8085/createUser", {...formFieldValues});

    setStepNumber(4);
    setSuccess(true);
  };

  const generatePassword = () => {
    let charset = "";
    let newPassword = "";

    charset += "0123456789";
    charset += "abcdefghijklmnopqrstuvwxyz";
    charset += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    for (let i = 0; i <= 8; i++) {
      newPassword += charset.charAt(Math.floor(Math.random() * charset.length));
    }

    setGeneratedPassword(newPassword);
  };

  const nextOrSubmitButtonHandler = () => {
    if (stepNumber === 4) history("/login");
    else if (stepNumber !== FINAL_STEP_NUMBER)
      setStepNumber((prevState) => prevState + 1);
    else handleFormSubmit();
  };

  const handleFormValueChange = (e) => {
    const { name, value } = e.target;
    setFormFieldValues((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleResidentialAddressFieldChange = (e) => {
    const { name, value } = e.target;
    setFormFieldValues((prevState) => ({
      ...prevState,
      residentialAddress: {
        ...prevState.residentialAddress,
        [name]: value,
      },
    }));
  };

  const handlePermanentAddressFieldChange = (e) => {
    const { name, value } = e.target;
    setFormFieldValues((prevState) => ({
      ...prevState,
      permanentAddress: {
        ...prevState.permanentAddress,
        [name]: value,
      },
    }));
  };

  useEffect(() => {
    console.log(formFieldValues);
  }, [formFieldValues]);

  const getElementForCurrentStep = () => {
    switch (stepNumber) {
      case 1:
        return (
          <PersonalDetailsForm handleFormValueChange={handleFormValueChange} />
        );
      case 2:
        return (
          <AddressDetailsForm
            handleResidentialAddressFieldChange={
              handleResidentialAddressFieldChange
            }
            handlePermanentAddressFieldChange={
              handlePermanentAddressFieldChange
            }
          />
        );
      case 3:
        return (
          <OccupationDetailsForm
            handleFormValueChange={handleFormValueChange}
          />
        );

      case 4:
        return <AccountOpenRedirect generatedPassword={generatedPassword} />;

      default:
        return <></>;
    }
  };

  return (
    <div className="form-bg my-5 mx-auto">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-offset-3 col-md-8 col-sm-offset-2 col-sm-12">
            <div className="form-container">
              {success ? (
                <h3 className="title">
                  Form for Account Opening has been completed successfully!
                </h3>
              ) : (
                <h3 className="title">Open an Account</h3>
              )}
              <div className="progress mb-3" style={{ height: "5px" }}>
                <div
                  className="progress-bar progress-bg"
                  style={{
                    width: success ? "100%" : `${33 * (stepNumber - 1)}%`,
                  }}
                ></div>
              </div>
              {getElementForCurrentStep()}
              <div className="container">
                <div className="row">
                  <div className="col">
                    <div className="form-horizontal mb-0">
                      <div className="d-flex justify-content-between">
                        {stepNumber > 1 && stepNumber <= 3 ? (
                          <button
                            className="btn btn-warning mx-0 my-0"
                            type="submit"
                            onClick={() => {
                              setStepNumber((prevState) => prevState - 1);
                            }}
                          >
                            <i className="bi bi-caret-left-fill"></i>
                            Back
                            <span></span>
                          </button>
                        ) : (
                          <div></div>
                        )}
                        <button
                          className="btn btn-success mx-0 my-0"
                          type="submit"
                          onClick={nextOrSubmitButtonHandler}
                        >
                          {stepNumber === 3 ? (
                            "Submit"
                          ) : stepNumber === 4 ? (
                            <>
                              Login
                              <i className="bi bi-caret-right-fill"></i>
                            </>
                          ) : (
                            <>
                              Next
                              <i className="bi bi-caret-right-fill"></i>
                            </>
                          )}
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OpenAccount;
