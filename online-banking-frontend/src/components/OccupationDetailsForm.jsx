import React from "react";

const OccupationDetailsForm = (props) => {
  const { formFieldValues, handleFormValueChange, formFieldErrorValues } =
    props;
  return (
    <>
      <div className="d-flex justify-content-start mb-2">
        <h4 className="sub-title">Occupation Details</h4>
      </div>
      <form className="form-horizontal">
        <div className="container">
          <div className="row">
            <div className="col-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.occupation.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.occupation}
                  </label>
                )}
                <label>Occupation Type*</label>

                <select
                  name="occupation"
                  className="form-control form-select"
                  onChange={handleFormValueChange}
                  value={formFieldValues.occupation}
                >
                  <option value="">---</option>
                  <option value="Government Employee">
                    Government Employee
                  </option>
                  <option value="Private Sector Employee">
                    Private Sector Employee
                  </option>
                  <option value="Business owner">Business owner</option>
                  <option value="Self Employed">Self Employed</option>
                  <option value="Student">Student</option>
                  <option value="Other">Other</option>
                </select>
              </div>
            </div>
            <div className="col-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.sourceOfIncome.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.sourceOfIncome}
                  </label>
                )}

                <label>Source of income*</label>
                <select
                  name="sourceOfIncome"
                  className="form-control form-select"
                  onChange={handleFormValueChange}
                  value={formFieldValues.sourceOfIncome}
                >
                  <option value="">---</option>
                  <option value="Employment Salary/Wages">
                    Employment Salary/Wages
                  </option>
                  <option value="Self-Employment Income">
                    Self-Employment Income
                  </option>
                  <option value="Business Profits" d>
                    Business Profits
                  </option>
                  <option value="Rental">Rental</option>
                  <option value="Investment">Investment</option>
                  <option value="Other">Other</option>
                </select>
              </div>
            </div>
            <div className="col-12">
              <div className="form-group d-inline-flex flex-column align-items-start w-100">
                {formFieldErrorValues.grossAnnualIncome.length != 0 && (
                  <label className="text-danger">
                    {formFieldErrorValues.grossAnnualIncome}
                  </label>
                )}
                <label>Gross Annual Income*</label>
                <select
                  name="grossAnnualIncome"
                  className="form-control form-select"
                  onChange={handleFormValueChange}
                  value={formFieldValues.grossAnnualIncome}
                >
                  <option value="">---</option>
                  <option value="< Rs.2,50,000">{"<"} Rs.2,50,000</option>
                  <option value="Rs.2,50,000 - Rs.5,00,000">
                    Rs.2,50,000 - Rs.5,00,000
                  </option>
                  <option value="Rs.5,00,000 - Rs.10,00,000">
                    Rs.5,00,000 - Rs.10,00,000
                  </option>
                  <option value="> Rs.10,00,000">{">"} Rs.10,00,000</option>
                </select>
              </div>
            </div>
          </div>
        </div>
      </form>
      {/* <div className='d-flex justify-content-start mb-2'>
                <h4 className="sub-title">Banking Details</h4>
            </div>
            <form className="form-horizontal">
                <div className="container">
                    <div className="row">
                        <div className="col-12">
                            <div className="form-group d-inline-flex w-100">
                                <label className='w-50'>
                                    <p className='h6 text-start'>Do you want a Debit/ATM card?*</p>
                                </label>
                                <div className='mx-5 d-flex'>
                                    <div className='mx-2 form-check'>
                                        <input type='radio' id='yes' name='debit_check' />
                                        <label for='yes' className='px-2 form-check-label'>
                                            <p className='h6'>Yes</p>
                                        </label>
                                    </div>
                                    <div className='mx-2 form-check'>
                                        <input type='radio' id='no' name='debit_check' />
                                        <label for='no' className='px-2 form-check-label'>
                                            <p className='h6'>No</p>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-12">
                            <div className="form-group d-inline-flex w-100">
                                <label className='w-50'>
                                    <p className='h6 text-start'>Opt for NetBanking?*</p>
                                </label>
                                <div className='mx-5 d-flex'>
                                    <div className='mx-2 form-check'>
                                        <input type='radio' id='yes' name='netbanking_check' />
                                        <label for='yes' className='px-2 form-check-label'>
                                            <p className='h6'>Yes</p>
                                        </label>
                                    </div>
                                    <div className='mx-2 form-check'>
                                        <input type='radio' id='no' name='netbanking_check' />
                                        <label for='no' className='px-2 form-check-label'>
                                            <p className='h6'>No</p>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div >
            </form > */}
    </>
  );
};

export default OccupationDetailsForm;
