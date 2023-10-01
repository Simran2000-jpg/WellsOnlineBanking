import React from "react";

const PersonalDetailsForm = (props) => {
  const { formFieldValues, handleFormValueChange, formFieldErrorValues } =
    props;
  return (
    <>
      <div className="d-flex justify-content-start mb-2">
        <h4 className="sub-title">Personal Details</h4>
      </div>
      
      <form className="form-horizontal" style={{ marginTop: "30px" }}>
        <div className="container">
          <div className="row">
            <div className="col-md-3 col-sm-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.firstName.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.firstName}
                  </label>
                )}
                <label>First Name*</label>
                <input
                  name="firstName"
                  className="form-control"
                  type="text"
                  value={formFieldValues.firstName}
                  onChange={handleFormValueChange}
                />
              </div>
            </div>
            <div className="col-md-3 col-sm-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.middleName.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.middleName}
                  </label>
                )}
                <label>Middle Name</label>
                <input
                  name="middleName"
                  className="form-control"
                  type="text"
                  value={formFieldValues.middleName}
                  onChange={handleFormValueChange}
                />
              </div>
            </div>
            <div className="col-md-3 col-sm-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.lastName.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.lastName}
                  </label>
                )}

                <label>Last Name*</label>
                <input
                  name="lastName"
                  className="form-control"
                  type="text"
                  value={formFieldValues.lastName}
                  onChange={handleFormValueChange}
                />
              </div>
            </div>
            <div className="col-md-3 col-sm-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.gender.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.gender}
                  </label>
                )}
                <label>Gender*</label>
                <select
                  name="gender"
                  className="form-select form-control"
                  onChange={handleFormValueChange}
                  value={formFieldValues.gender}
                >
                  <option value="">---</option>
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                  <option value="Others">Others</option>
                </select>
              </div>
            </div>
          </div>
          <div className="row">
            <div className="col-md-6 col-sm-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.phoneNumber.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.phoneNumber}
                  </label>
                )}
                <label>Mobile Number*</label>
                <input
                  name="phoneNumber"
                  className="form-control"
                  type="text"
                  maxLength={10}
                  value={formFieldValues.phoneNumber}
                  onChange={handleFormValueChange}
                />
              </div>
            </div>
            <div className="col-md-6 col-sm-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.emailId.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.emailId}
                  </label>
                )}

                <label>Email ID*</label>
                <input
                  name="emailId"
                  className="form-control"
                  type="email"
                  value={formFieldValues.emailId}
                  onChange={handleFormValueChange}
                />
              </div>
            </div>
          </div>
          <div className="row">
            <div className="col-md-6 col-sm-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.aadharNumber.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.aadharNumber}
                  </label>
                )}
                <label>Aadhaar Card Number*</label>
                <input
                  name="aadharNumber"
                  className="form-control"
                  type="text"
                  maxLength={12}
                  value={formFieldValues.aadharNumber}
                  onChange={handleFormValueChange}
                />
              </div>
            </div>
            <div className="col-md-6 col-sm-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.panNumber.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.panNumber}
                  </label>
                )}
                <label>PAN Card Number*</label>
                <input
                  name="panNumber"
                  className="form-control"
                  type="text"
                  maxLength={10}
                  value={formFieldValues.panNumber}
                  onChange={handleFormValueChange}
                />
              </div>
            </div>
          </div>
          <div className="row">
            <div className="col-md-6 col-sm-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.dob.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.dob}
                  </label>
                )}
                <label>Date of Birth*</label>
                <input
                  name="dob"
                  className="form-control"
                  type="date"
                  value={formFieldValues.dob}
                  onChange={handleFormValueChange}
                />
              </div>
            </div>
            <div className="col-md-6 col-sm-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.fatherName.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.fatherName}
                  </label>
                )}
                <label>Father's Name*</label>
                <input
                  name="fatherName"
                  className="form-control"
                  type="text"
                  value={formFieldValues.fatherName}
                  onChange={handleFormValueChange}
                />
              </div>
            </div>
          </div>
        </div>
      </form>
    </>
  );
};

export default PersonalDetailsForm;
