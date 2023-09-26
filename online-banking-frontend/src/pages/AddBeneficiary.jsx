import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';

function AddBeneficiary() {
    const navigate = useNavigate();
    const [beneficiaryName, setBeneficiaryName] = useState('');
    const [accountNumber, setAccountNumber] = useState('');
    const [confirmAccountNumber, setConfirmAccountNumber] = useState('');
    const [ifscCode, setIfscCode] = useState('');    
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    const userId = localStorage.getItem('userId')

    const createBeneficiary = async () => {
        try {
            const response = await axios.post(`http://localhost:8085/beneficiaries/${userId}`, {
                ifscCode: ifscCode,
                accountNo: accountNumber,
                name: beneficiaryName
            });
            if(response.status == 200){
                setError(false);
                setSuccessMessage("Added Beneficiary successful");
                setTimeout(() => {
                    navigate("/dashboard/view-beneficiary");
                  }, 2000);

            }else{
                //add controller error message in backend
            }    
        } catch (error) {
            setError("Error adding Beneficiary");
        }
    };

    const handleAddBeneficiary = (e) => {
        e.preventDefault();
        if (accountNumber === confirmAccountNumber) {
            createBeneficiary();
        } else {
            setError('Account numbers do not match.');
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
                                    {successMessage && <div className="alert alert-success"><label>{successMessage}</label></div>}
                                    {error && <div className="alert alert-danger"><label>{error}</label></div>}
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
