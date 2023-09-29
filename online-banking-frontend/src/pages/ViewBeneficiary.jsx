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

  useEffect(() => {
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
  }, []);

  const routeAddBeneficiary = () => {
    navigate("/dashboard/add-beneficiary");
  };

  const handleDelete = (index) => {
    const deletedBeneficiary = beneficiaries[index];
    console.log("Deleted beneficiary:", deletedBeneficiary);
  };

  return (
    <div>
      <SidebarComponent />

      <div>
        <h1>View Beneficiaries</h1>
        <button
          className="btn btn-success btn-add"
          onClick={routeAddBeneficiary}
        >
          <FontAwesomeIcon icon={faPlus} />
          Add Beneficiary
        </button>
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
                    <FontAwesomeIcon icon={faTrash} />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ViewBeneficiary;
