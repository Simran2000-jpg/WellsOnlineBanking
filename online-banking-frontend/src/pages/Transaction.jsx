import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Transaction() {
    const [fromAccount, setFromAccount] = useState('');
    const [toAccount, setToAccount] = useState('');
    const [amount, setAmount] = useState();
    const [transactionPassword, setTransactionPassword] = useState('');
    const [remarks, setRemarks] = useState('');
    const [toAccountOptions, setToAccountOptions] = useState([]);
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    const createBeneficiary = async (ifscCode, accountNo) => {
        try {
            const response = await axios.post('http://localhost:8085/beneficiaries/100', {
                ifscCode: ifscCode,
                accountNo: accountNo,
            });
        } catch (error) {
            console.error('Error fetching account options:', error);
        }
    };

    useEffect(() => {
        const fetchToAccountOptions = async () => {
            try {
                const response = await axios.get(' http://localhost:8085/beneficiaries');
                console.log(response);
                setToAccountOptions(response.data);
            } catch (error) {
                setError('Error fetching account options:')
                console.error('Error fetching account options:', error);
            }
        };
        fetchToAccountOptions();
    }, []);

    const areAllFieldsFilled = () => {
        return (
          fromAccount !== '' &&
          toAccount !== '' &&
          amount !== '' &&
          transactionPassword !== ''
        );
      };

    const initiateTransaction = async () => {
        try {
            const response = await axios.post(`http://localhost:8085/transactions/${fromAccount}/${toAccount}`,
                {
                    "amount": amount,
                    "transactionPassword": transactionPassword,
                    "remarks": remarks
                },
                {
                    headers: { "Content-Type": "multipart/form-data" }
                });
            console.log(response);
            setError(false)
            setSuccessMessage("Transaction Successful")
        } catch (error) {
            setError(error.response.data.message)
            console.log(error.response.data.message);
        }
    };

    const handleTransaction = (e) => {
        e.preventDefault();
        console.log('From Account:', fromAccount);
        console.log('To Account:', toAccount);
        console.log('Amount:', amount);
        console.log('Transaction Password:', transactionPassword);
        initiateTransaction();
    };

    return (
        <div className="form-bg my-5 mx-auto">
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
                        <div className="form-container">
                            <h3 className="title">Transaction</h3>
                            <form className="form-vertical" onSubmit={handleTransaction}>
                                <div className="form-group">
                                    {successMessage && <div className="alert alert-success"><label>{successMessage}</label></div>}
                                    {error && <div className="alert alert-danger"><label>{error}</label></div>}

                                    <label>From Account*</label>
                                    <input
                                        className="form-control"
                                        type="text"
                                        value={fromAccount}
                                        onChange={(e) => setFromAccount(e.target.value)}
                                    />
                                </div>
                                <div className="form-group">
                                    <label>To Account*</label>
                                    <select
                                        className="form-control"
                                        value={toAccount}
                                        onChange={(e) => setToAccount(e.target.value)}
                                    >
                                        <option value="">Select To Account</option>
                                        {toAccountOptions.map((option) => (
                                            <option key={option.bid} value={option.bid}>
                                                {option.accountNo}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                                <div className="form-group">
                                    <label>Amount*</label>
                                    <input
                                        className="form-control"
                                        type="text"
                                        value={amount}
                                        onChange={(e) => setAmount(e.target.value)}
                                    />
                                </div>
                                <div className="form-group">
                                    <label>Transaction Password*</label>
                                    <input
                                        className="form-control"
                                        type="password"
                                        value={transactionPassword}
                                        onChange={(e) => setTransactionPassword(e.target.value)}
                                    />
                                </div>
                                <div className="form-group">
                                    <label>Remarks</label>
                                    <input
                                        className="form-control"
                                        type="text"
                                        value={remarks}
                                        onChange={(e) => setRemarks(e.target.value)}
                                    />
                                </div>
                                <div className="text-center mb-4">
                                    <button className="my-4 mx-auto btn btn-success" type="submit" disabled={!areAllFieldsFilled()} >
                                        Initiate Transaction
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

export default Transaction;
