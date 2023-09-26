import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import "../styles/ViewBeneficiary.css";

const ViewBeneficiary = () => {
  // const beneficiaries = [
  //     { name: "Beneficiary 1", ifsc: "IFSC123", accountNumber: "1234567890" },
  //     { name: "Beneficiary 2", ifsc: "IFSC456", accountNumber: "9876543210" },
  // ];

  const [beneficiaries, setBeneficiaries] = useState([]);

  useEffect(() => {
    const apiUrl = "https://your-api-endpoint-here";
    axios
      .get(apiUrl)
      .then((response) => {
        setBeneficiaries(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data: ", error);
      });
  }, []);

  const handleDelete = (index) => {
    const deletedBeneficiary = beneficiaries[index];
    console.log("Deleted beneficiary:", deletedBeneficiary);
  };

  return (
    <div>
      <h1>View Beneficiaries</h1>
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
              <td>{beneficiary.ifsc}</td>
              <td>{beneficiary.accountNumber}</td>
              <td>
                <button onClick={() => handleDelete(index)}>
                  {/* <i class="bi bi-trash"></i> */}
                  <FontAwesomeIcon icon={faTrash} />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewBeneficiary;
