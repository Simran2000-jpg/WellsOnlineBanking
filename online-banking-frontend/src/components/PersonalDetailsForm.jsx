import React from 'react'

const PersonalDetailsForm = (props) => {
    const { handleFormValueChange } = props;
    return (
        <>
            <div className='d-flex justify-content-start mb-2'>
                <h4 className="sub-title">Personal Details</h4>
            </div>
            <form className="form-horizontal">
                <div className="container">
                    <div className="row">
                        <div className="col-md-2 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Title*</label>
                                <select className="form-select form-control">
                                    <option value="Mr">Mr.</option>
                                    <option value="Mrs">Mrs.</option>
                                    <option value="Ms">Ms.</option>
                                    <option value="Mx">Mx.</option>
                                    <option value="Dr">Dr.</option>
                                </select>
                            </div>
                        </div>
                        <div className="col-md-3 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>First Name*</label>
                                <input name="firstName" className="form-control" type="text" onChange={handleFormValueChange} />
                            </div>
                        </div>
                        <div className="col-md-3 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Middle Name</label>
                                <input name="middleName" className="form-control" type="text" onChange={handleFormValueChange} />
                            </div>
                        </div>
                        <div className="col-md-4 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Last Name*</label>
                                <input name="lastName" className="form-control" type="text" onChange={handleFormValueChange} />
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        {/* <div className="col-md-2 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Sex*</label>
                                <select name="gender" className="form-control form-select" onChange={handleFormValueChange} defaultValue="">
                                    <option value=""></option>
                                    <option value="male">{"<"} Rs.2,50,000</option>
                                    <option value="female">Rs.2,50,000 - Rs.5,00,000</option>
                                    <option value="other">Rs.5,00,000 - Rs.10,00,000</option>
                                </select>
                            </div>
                        </div> */}
                        <div className="col-md-6 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Mobile Number*</label>
                                <input name="phoneNumber" className="form-control" type="text" maxLength={10} onChange={handleFormValueChange} />
                            </div>
                        </div>
                        <div className="col-md-6 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Email ID*</label>
                                <input name="emailId" className="form-control" type="email" onChange={handleFormValueChange} />
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-6 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Aadhaar Card Number*</label>
                                <input name="aadharNumber" className="form-control" type="text" maxLength={12} onChange={handleFormValueChange} />
                            </div>
                        </div>
                        <div className="col-md-6 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>PAN Card Number*</label>
                                <input name="panNumber" className="form-control" type="text" maxLength={10} onChange={handleFormValueChange} />
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-6 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Date of Birth*</label>
                                <input name="dob" className="form-control" type="date" onChange={handleFormValueChange} />
                            </div>
                        </div>
                        <div className="col-md-6 col-sm-12">
                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                <label>Father's Name*</label>
                                <input className="form-control" type="text" />
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </>
    )
}

export default PersonalDetailsForm