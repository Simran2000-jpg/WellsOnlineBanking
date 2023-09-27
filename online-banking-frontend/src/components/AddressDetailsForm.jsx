import React from "react";

const AddressDetailsForm = (props) => {
  const {
    formFieldValues,
    handleResidentialAddressFieldChange,
    handlePermanentAddressFieldChange,
    formFieldErrorValues,
  } = props;

  return (
    <>
      <div className="d-flex justify-content-start mb-2">
        <h4 className="sub-title">Address Details</h4>
      </div>
      <form className="form-horizontal">
        <div className="container">
          <div className="row">
            <div className="col-6">
              <div className="d-inline-flex flex-column align-items-start w-100">
                <p className="h6">Residential Address</p>
                <div className="container">
                  <div className="row">
                    <div className="col px-0">
                      <div className="form-group d-inline-flex flex-column align-items-start w-100">
                        {formFieldErrorValues.residentialAddress.address
                          .length != 0 && (
                          <label className="text-danger">
                            {formFieldErrorValues.residentialAddress.address}
                          </label>
                        )}
                        <label>Address *</label>
                        <input
                          name="address"
                          className="form-control"
                          type="text"
                          value={formFieldValues.residentialAddress.address}
                          onChange={handleResidentialAddressFieldChange}
                        />
                      </div>
                    </div>
                  </div>

                  <div className="row">
                    <div className="col px-0">
                      <div className="form-group d-inline-flex flex-column align-items-start w-100">
                        {formFieldErrorValues.residentialAddress.city.length !=
                          0 && (
                          <label className="text-danger">
                            {formFieldErrorValues.residentialAddress.city}
                          </label>
                        )}

                        <label>City*</label>
                        <input
                          name="city"
                          className="form-control"
                          type="text"
                          value={formFieldValues.residentialAddress.city}
                          onChange={handleResidentialAddressFieldChange}
                        />
                      </div>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col px-0">
                      <div className="form-group d-inline-flex flex-column align-items-start w-100">
                        {formFieldErrorValues.residentialAddress.state.length !=
                          0 && (
                          <label className="text-danger">
                            {formFieldErrorValues.residentialAddress.state}
                          </label>
                        )}

                        <label>State*</label>
                        <input
                          name="state"
                          className="form-control"
                          type="text"
                          value={formFieldValues.residentialAddress.state}
                          onChange={handleResidentialAddressFieldChange}
                        />
                      </div>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col px-0">
                      <div className="form-group d-inline-flex flex-column align-items-start w-100">
                        {formFieldErrorValues.residentialAddress.pincode !=
                          0 && (
                          <label className="text-danger">
                            {formFieldErrorValues.residentialAddress.pincode}
                          </label>
                        )}

                        <label>Pincode*</label>
                        <input
                          name="pincode"
                          className="form-control"
                          type="number"
                          maxLength={6}
                          value={formFieldValues.residentialAddress.pincode}
                          onChange={handleResidentialAddressFieldChange}
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-6">
              <div className="d-inline-flex flex-column align-items-start w-100">
                <p className="h6">Permanent Address</p>
                <div className="container">
                  <div className="row">
                    <div className="col px-0">
                      <div className="form-group d-inline-flex flex-column align-items-start w-100">
                        {formFieldErrorValues.permanentAddress.address.length !=
                          0 && (
                          <label className="text-danger">
                            {formFieldErrorValues.permanentAddress.address}
                          </label>
                        )}
                        <label>Address *</label>
                        <input
                          name="address"
                          className="form-control"
                          type="text"
                          value={formFieldValues.permanentAddress.address}
                          onChange={handlePermanentAddressFieldChange}
                        />
                      </div>
                    </div>
                  </div>

                  <div className="row">
                    <div className="col px-0">
                      <div className="form-group d-inline-flex flex-column align-items-start w-100">
                        {formFieldErrorValues.permanentAddress.city.length !=
                          0 && (
                          <label className="text-danger">
                            {formFieldErrorValues.permanentAddress.city}
                          </label>
                        )}
                        <label>City*</label>
                        <input
                          name="city"
                          className="form-control"
                          type="text"
                          value={formFieldValues.permanentAddress.city}
                          onChange={handlePermanentAddressFieldChange}
                        />
                      </div>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col px-0">
                      <div className="form-group d-inline-flex flex-column align-items-start w-100">
                        {formFieldErrorValues.permanentAddress.state.length !=
                          0 && (
                          <label className="text-danger">
                            {formFieldErrorValues.permanentAddress.state}
                          </label>
                        )}
                        <label>State*</label>
                        <input
                          name="state"
                          className="form-control"
                          type="text"
                          value={formFieldValues.permanentAddress.state}
                          onChange={handlePermanentAddressFieldChange}
                        />
                      </div>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col px-0">
                      <div className="form-group d-inline-flex flex-column align-items-start w-100">
                        {formFieldErrorValues.permanentAddress.pincode != 0 && (
                          <label className="text-danger">
                            {formFieldErrorValues.permanentAddress.pincode}
                          </label>
                        )}

                        <label>Pincode*</label>
                        <input
                          name="pincode"
                          className="form-control"
                          type="number"
                          maxLength={6}
                          value={formFieldValues.permanentAddress.pincode}
                          onChange={handlePermanentAddressFieldChange}
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </>
  );
};

export default AddressDetailsForm;
