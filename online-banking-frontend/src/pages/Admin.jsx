import React, { useEffect } from "react";
import { Users } from "../data/Users";
import { useNavigate } from "react-router-dom";
const Admin = () => {
  const navigate = useNavigate();
  const fetchNonVerifiedUsers = () => {
    return Users.filter((user) => user.kyc === false);
  };

  const viewUser = (userId) => {
    console.log(userId);
    navigate("/user/" + userId);
  };
  useEffect(() => {
    console.log(fetchNonVerifiedUsers());
  }, []);

  return (
    <div className="form-bg my-5 mx-auto container">
      <div className="row justify-content-center">
        <div className="col-md-offset-3 col-12 col-sm-offset-2">
          <div className="form-container">
            <h2 className="title">Admin Dashboard</h2>
            <div className="card col-12">
              <h3 className="card-header">OPEN REQUESTS</h3>
              <div className="card-body">
                <table className="table table-striped">
                  <thead>
                    <tr>
                      <th scope="col">User ID</th>
                      <th scope="col">Name</th>
                      <th scope="col">Aadhar Number</th>
                      <th scope="col">KYC Status</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    {fetchNonVerifiedUsers().map((user) => (
                      <tr>
                        <th scope="row">{user.userId}</th>
                        <td>{user.firstName + " " + user.lastName}</td>
                        <td>{user.aadharNumber}</td>
                        <td>{user.kyc ? "Verified" : "Not Verified"}</td>
                        <td>
                          <button className="btn btn-success">Verify</button>
                          &nbsp;
                          <button
                            className="btn btn-primary"
                            onClick={() => viewUser(user.userId)}
                          >
                            View
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

export default Admin;
