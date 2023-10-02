import React, { useContext, useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPlus } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";
import "../styles/ViewBeneficiary.css";
import SidebarComponent from "../components/SidebarComponent";
import { Context } from "../context/Context";
import UserService from "../services/UserService";
import BeneficiaryService from "../services/BeneficiaryService";

const ViewBeneficiary = () => {
    const navigate = useNavigate();

    const { userId, dispatch } = useContext(Context);

    const [kyc, setKyc] = useState(false);
    const [beneficiaries, setBeneficiaries] = useState([]);

    const viewBeneficiary = () => {
        BeneficiaryService.viewBeneficiary(userId).then((response) => {
            if (response.status === 200) {
                setBeneficiaries(response.data);
            }
            else {
                console.error("Error fetching data: ", response.data);
            }
        })
    }

    useEffect(() => {
        viewBeneficiary();
        UserService.getUserDetails(userId).then((response) => {
            setKyc(response.kyc);
        });
    }, []);

    const routeAddBeneficiary = () => {
        navigate("/dashboard/add-beneficiary");
    };

    const handleDelete = async (index) => {
        const deletedBeneficiary = beneficiaries[index];
        BeneficiaryService.deleteBeneficiary(deletedBeneficiary.bid).then((response) => {
            if (response.status === 200) {
                viewBeneficiary();
            } else {
                console.log(response.response.data);
            }
        })
    };

    return (
        <>
            <SidebarComponent />
            <div className="form-bg my-5 mx-auto">
                <div className="container">
                    <div className="row justify-content-center">
                        <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
                            <div className="form-container">
                                {kyc ? (
                                    <h3 className="title">View Beneficiary</h3>
                                ) : (
                                    <h3 className="title" style={{ color: "red" }}>
                                        Contact Admin for KYC verification
                                    </h3>
                                )}
                                {kyc && (
                                    <>
                                        <div className="text-center">
                                            <button
                                                className="my-1 btn btn-primary"
                                                onClick={routeAddBeneficiary}
                                            >
                                                Add Beneficiary
                                            </button>
                                        </div>
                                        {beneficiaries.length > 0 ? (
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
                                                                <button
                                                                    className="btn btn-danger"
                                                                    onClick={() => handleDelete(index)}
                                                                >
                                                                    {/* <i class="bi bi-trash"></i> */}
                                                                    <FontAwesomeIcon icon={faTrash} />
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    ))}
                                                </tbody>
                                            </table>
                                        ) : (
                                            <h3>No Beneficiary Added</h3>
                                        )}
                                    </>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default ViewBeneficiary;
