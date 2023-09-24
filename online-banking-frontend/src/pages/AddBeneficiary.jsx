import React, { useState } from 'react';

function AddBeneficiary() {
    const [beneficiaryName, setBeneficiaryName] = useState('');
    const [accountNumber, setAccountNumber] = useState('');
    const [confirmAccountNumber, setConfirmAccountNumber] = useState('');
    const [ifscCode, setIfscCode] = useState('');

    const handleAddBeneficiary = (e) => {
        e.preventDefault();
        if (accountNumber === confirmAccountNumber) {
            console.log('Beneficiary added:', beneficiaryName, accountNumber, ifscCode);
        } else {
            console.log('Account numbers do not match.');
        }
    };

    return (
        <div className="form-bg my-5 mx-auto">
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
                        <div className="form-container">
                            <h3 className="title">Add Beneficiary</h3>
                            <form className="form-vertical" onSubmit={handleAddBeneficiary}>
                                <div className="form-group">
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
                                    <button className="my-4 mx-auto btn btn-success" type="submit">
                                        Add Beneficiary
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AddBeneficiary;
