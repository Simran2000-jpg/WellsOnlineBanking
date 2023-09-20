import React from 'react'

const AddressDetailsForm = () => {
    return (
        <>

            <div className='d-flex justify-content-start mb-2'>
                <h4 className="sub-title">Address Details</h4>
            </div>
            <form className="form-horizontal">
                <div className="container">
                    <div className="row">
                        <div className="col-6">
                            <div className="d-inline-flex flex-column align-items-start w-100">
                                <p className='h6'>Residential Address</p>
                                <div className="container">
                                    <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>Address Line 1*</label>
                                                <input className="form-control" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>Address Line 2*</label>
                                                <input className="form-control" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    {/* <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>Landmark</label>
                                                <input className="form-control" type="text" />
                                            </div>
                                        </div>
                                    </div> */}
                                    <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>City*</label>
                                                <input className="form-control" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>State*</label>
                                                <input className="form-control" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>Pincode*</label>
                                                <input className="form-control" type="number" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-6">
                            <div className="d-inline-flex flex-column align-items-start w-100">
                                <p className='h6'>Permanent Address</p>
                                <div className="container">
                                    <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>Address Line 1*</label>
                                                <input className="form-control" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>Address Line 2*</label>
                                                <input className="form-control" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    {/* <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>Landmark</label>
                                                <input className="form-control" type="text" />
                                            </div>
                                        </div>
                                    </div> */}
                                    <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>City*</label>
                                                <input className="form-control" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>State*</label>
                                                <input className="form-control" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col px-0">
                                            <div className="form-group d-inline-flex flex-column align-items-start w-100">
                                                <label>Pincode*</label>
                                                <input className="form-control" type="number" />
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
    )
}

export default AddressDetailsForm