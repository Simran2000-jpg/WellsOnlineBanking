import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPlus } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/ViewBeneficiary.css";
import SidebarComponent from "../components/SidebarComponent";

const ViewBeneficiary = () => {
    const navigate = useNavigate();
    const [beneficiaries, setBeneficiaries] = useState([]);
    const userId = localStorage.getItem("userId");

    const viewBeneficiary = () => {
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
        viewBeneficiary()
    }, []);

    const routeAddBeneficiary = () => {
        navigate("/dashboard/add-beneficiary");
    };

    const handleDelete = async (index) => {
        const deletedBeneficiary = beneficiaries[index];
        try {
            const response = await axios.delete(`http://localhost:8085/beneficiaries/${deletedBeneficiary.bid}`);
            if (response.status === 200) {
                viewBeneficiary();
            } else {
                console.log("Error in deleting");
            }
        } catch (error) {
            console.log(error.response.data);
        }
    };

    return (  
        <>
        <SidebarComponent/>
        <div className="form-bg my-5 mx-auto">
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8">
                        <div className="form-container">
                            <h3 className="title">View Beneficiary</h3>
                            <div className="text-center">
                                     <button
                                         className="my-1 btn btn-primary"
                                         onClick={routeAddBeneficiary}
                                     >
                                         Add Beneficiary
                                     </button>
                                </div>
                                {beneficiaries.length > 0 ?
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
                                    :
                                    <h3>No Beneficiary Added</h3>
                                }
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </>
    );
};

export default ViewBeneficiary;
