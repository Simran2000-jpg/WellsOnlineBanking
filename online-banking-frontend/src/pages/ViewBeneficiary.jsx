import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPlus } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/ViewBeneficiary.css";

const ViewBeneficiary = () => {
    const navigate = useNavigate();
    const [beneficiaries, setBeneficiaries] = useState([]);
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const userId = localStorage.getItem('userId')

    const getBeneficiaries = () => {
        const apiUrl = `http://localhost:8085/beneficiaries/${userId}`;
        axios
            .get(apiUrl)
            .then((response) => {
                console.log(response);
                setBeneficiaries(response.data);
            })
            .catch((error) => {
                console.error("Error fetching data: ", error);
            });
    }

    useEffect(() => {
        getBeneficiaries();
    }, []);

    const routeAddBeneficiary = () => {
        navigate("/dashboard/add-beneficiary");
    }

    const handleDelete = async (index) => {
        const deletedBeneficiary = beneficiaries[index];
        try {
            const response = await axios.delete(`http://localhost:8085/beneficiaries/${deletedBeneficiary.bid}`);
            if (response.status === 200) {
                setError(false);
                setSuccessMessage(response.data);
                getBeneficiaries();
            } else {
                setError("Error in deleting");
            }
        } catch (error) {
            setError(error.response.data);
        }
    };

    {/* <button className="btn btn-success btn-add" onClick={routeAddBeneficiary} >
                                <FontAwesomeIcon icon={faPlus} />
                                Add Beneficiary
                            </button> */}
    return (
        <div className="form-bg my-5 mx-auto">
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
                        <div className="form-container">
                             <h3 className="title">Transaction</h3>
                                <button className="btn btn-success btn-add" onClick={routeAddBeneficiary} >
                                    <FontAwesomeIcon icon={faPlus} />
                                    Add Beneficiary
                                </button>
                                <div className="form-group">
                                    {/* {successMessage && <div className="alert alert-success"><br /><label>{successMessage}</label></div>}
                                    {error && <div className="alert alert-danger"><label>{error}</label></div>} */}
                                    <table className="table">
                                        <thead>
                                            <tr>
                                                <th>Beneficiary Name</th>
                                                <th>IFSC Code</th>
                                                <th>Account Number</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {beneficiaries.map((beneficiary, index) => (
                                                <tr key={index}>
                                                    <td>{beneficiary.name}</td>
                                                    <td>{beneficiary.ifscCode}</td>
                                                    <td>{beneficiary.accountNo}</td>
                                                    <td>
                                                        <button className="btn btn-danger" onClick={() => handleDelete(index)}>
                                                            {/* <i class="bi bi-trash"></i> */}
                                                            <FontAwesomeIcon icon={faTrash} />
                                                        </button>
                                                    </td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ViewBeneficiary;
