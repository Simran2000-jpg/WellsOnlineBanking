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
  const [error, setError] = useState("");

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
  const [formFieldErrorValues, setFormFieldErrorValues] = useState(user);

  const validateFormValues = () => {
    let verified = true;
    if (formFieldValues.aadharNumber.length !== 12) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        aadharNumber: "Aadhar Number should be 12 digits",
      }));
      verified = false;
    }

    if (formFieldValues.panNumber.length !== 10) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        panNumber: "PAN Number should be 10 characters",
      }));
      verified = false;
    }

    if (formFieldValues.phoneNumber.length !== 10) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        phoneNumber: "Phone Number should be 10 digits",
      }));
      verified = false;
    }

    if (formFieldValues.emailId.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        emailId: "Email ID is required",
      }));
      verified = false;
    }

    if (formFieldValues.firstName.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        firstName: "First Name is required",
      }));
      verified = false;
    }

    if (formFieldValues.lastName.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        lastName: "Last Name is required",
      }));
      verified = false;
    }
    if (formFieldValues.dob.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        dob: "Date of Birth is required",
      }));
      verified = false;
    }
    if (formFieldValues.fatherName.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        fatherName: "Father's Name is required",
      }));
      verified = false;
    }
    if (formFieldValues.gender.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        gender: "Gender is required",
      }));
      verified = false;
    }
    if (formFieldValues.occupation.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        occupation: "Occupation is required",
      }));
      verified = false;
    }

    if (formFieldValues.sourceOfIncome.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        sourceOfIncome: "Source of Income is required",
      }));
      verified = false;
    }
    if (formFieldValues.grossAnnualIncome.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        grossAnnualIncome: "Gross Annual Income is required",
      }));
      verified = false;
    }
    if (formFieldValues.residentialAddress.address.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        residentialAddress: {
          ...prevState.residentialAddress,
          address: "Address is required",
        },
      }));
      verified = false;
    }
    if (formFieldValues.residentialAddress.city.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        residentialAddress: {
          ...prevState.residentialAddress,
          city: "City is required",
        },
      }));
      verified = false;
    }
    if (formFieldValues.residentialAddress.state.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        residentialAddress: {
          ...prevState.residentialAddress,
          state: "State is required",
        },
      }));
      verified = false;
    }
    if (formFieldValues.residentialAddress.pincode.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        residentialAddress: {
          ...prevState.residentialAddress,
          pincode: "Pincode is required",
        },
      }));
      verified = false;
    }
    if (formFieldValues.permanentAddress.address.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        permanentAddress: {
          ...prevState.permanentAddress,
          address: "Address is required",
        },
      }));
      verified = false;
    }
    if (formFieldValues.permanentAddress.city.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        permanentAddress: {
          ...prevState.permanentAddress,
          city: "City is required",
        },
      }));
      verified = false;
    }
    if (formFieldValues.permanentAddress.state.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        permanentAddress: {
          ...prevState.permanentAddress,
          state: "State is required",
        },
      }));
      verified = false;
    }
    if (formFieldValues.permanentAddress.pincode.length === 0) {
      setFormFieldErrorValues((prevState) => ({
        ...prevState,
        permanentAddress: {
          ...prevState.permanentAddress,
          pincode: "Pincode is required",
        },
      }));
      verified = false;
    }
    console.log(formFieldErrorValues);
    return verified;
  };

  const handleFormSubmit = async () => {
    if (!validateFormValues()) {
      setError("Please ensure all fields are entered properly");
      setStepNumber(1);
      return;
    }
    setError("");
    let tempPassword = generatePassword();
    let formFieldValuesCopy = formFieldValues;
    formFieldValuesCopy.loginPassword = tempPassword;
    setFormFieldValues(formFieldValuesCopy);

    axios
      .post("http://localhost:8085/createUser", {
        ...formFieldValuesCopy,
      })
      .then((res, err) => {
        console.log(res);
        setStepNumber(4);
        setSuccess(true);
        return;
      })
      .catch((err) => {
        setError(
          "Error in creating user, check if user details is already used"
        );
        console.log(err);
      });
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
    return newPassword;
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
    setFormFieldErrorValues((prevState) => ({
      ...prevState,
      [name]: "",
    }));
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
    setFormFieldErrorValues((prevState) => ({
      ...prevState,
      residentialAddress: {
        ...prevState.residentialAddress,
        [name]: "",
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
    setFormFieldErrorValues((prevState) => ({
      ...prevState,
      permanentAddress: {
        ...prevState.permanentAddress,
        [name]: "",
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
          <PersonalDetailsForm
            formFieldValues={formFieldValues}
            handleFormValueChange={handleFormValueChange}
            formFieldErrorValues={formFieldErrorValues}
          />
        );
      case 2:
        return (
          <AddressDetailsForm
            formFieldValues={formFieldValues}
            handleResidentialAddressFieldChange={
              handleResidentialAddressFieldChange
            }
            handlePermanentAddressFieldChange={
              handlePermanentAddressFieldChange
            }
            formFieldErrorValues={formFieldErrorValues}
          />
        );
      case 3:
        return (
          <OccupationDetailsForm
            formFieldValues={formFieldValues}
            handleFormValueChange={handleFormValueChange}
            formFieldErrorValues={formFieldErrorValues}
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
              {error.length != 0 && (
                <p className="alert alert-danger">{error}</p>
              )}
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
