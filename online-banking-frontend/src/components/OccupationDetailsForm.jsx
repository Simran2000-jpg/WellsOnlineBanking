import React from 'react'

const OccupationDetailsForm = (props) => {
    const { handleFormValueChange } = props;
    return (

        <>
            <div className='d-flex justify-content-start mb-2'>
                <h4 className="sub-title">Occupation Details</h4>
            </div>
            <form className="form-horizontal">
                <div className="container">
                    <div className="row">
                        <div className="col-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Occupation Type*</label>
                                <select name="occupation" className="form-control form-select" onChange={handleFormValueChange} defaultValue="">
                                    <option value=""></option>
                                    <option value="gov_employee">Government Employee</option>
                                    <option value="pvt_sector_employee">Private Sector Employee</option>
                                    <option value="business_owner">Business owner</option>
                                    <option value="self_employed">Self Employed</option>
                                    <option value="student">Student</option>
                                    <option value="other">Other</option>
                                </select>
                            </div>
                        </div>
                        <div className="col-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Source of income*</label>
                                <select name="incomeSource" className="form-control form-select" onChange={handleFormValueChange} defaultValue="">
                                    <option value=""></option>
                                    <option value="wages">Employment Salary/Wages</option>
                                    <option value="self_income">Self-Employment Income</option>
                                    <option value="business_profits">Business Profits</option>
                                    <option value="rental">Rental</option>
                                    <option value="investment">Investment</option>
                                    <option value="other">Other</option>
                                </select>
                            </div>
                        </div>
                        <div className="col-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Gross Annual Income*</label>
                                <select name="annualIncome" className="form-control form-select" onChange={handleFormValueChange} defaultValue="">
                                    <option value=""></option>
                                    <option value="low">{"<"} Rs.2,50,000</option>
                                    <option value="low_mid">Rs.2,50,000 - Rs.5,00,000</option>
                                    <option value="upper_mid">Rs.5,00,000 - Rs.10,00,000</option>
                                    <option value="high">{">"} Rs.10,00,000</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div className='d-flex justify-content-start mb-2'>
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
            </form >
        </>
    )
}

export default OccupationDetailsForm