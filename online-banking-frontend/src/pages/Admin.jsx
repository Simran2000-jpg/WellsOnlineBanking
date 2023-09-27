import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import AdminServices from "../services/AdminServices";
const Admin = () => {
  const navigate = useNavigate();
  const [users, setUsers] = useState([]);

  const fetchUsers = () => {
    AdminServices.fetchAllUsers().then((res) => {
      setUsers(res);
    });
  };

  const verifyUser = (userId, status) => {
    console.log("hello " + userId + " " + status);
    AdminServices.kycVerifyUser(userId, status).then((res) => {
      console.log(res);
      fetchUsers();
    });
  };

  const viewUser = (userId) => {
    console.log(userId);
    navigate("/user/" + userId);
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <div className="form-bg my-5 mx-auto container">
      <div className="row justify-content-center">
        <div className="col-md-offset-3 col-12 col-sm-offset-2">
          <div className="form-container">
            <h2 className="title">Admin Dashboard</h2>
            <div className="card col-12">
              <h3 className="card-header">OPEN KYC REQUESTS</h3>
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
                    {users
                      .filter((user) => user.kyc === false)
                      .map((user) => (
                        <tr>
                          <th scope="row">{user.uid}</th>
                          <td>{user.firstName + " " + user.lastName}</td>
                          <td>{user.aadharNumber}</td>
                          <td>
                            {user.kyc ? (
                              <p className="alert alert-success">Verified</p>
                            ) : (
                              <p className="alert alert-danger">Not Verified</p>
                            )}
                          </td>
                          <td>
                            <button
                              className="btn btn-success"
                              onClick={() => verifyUser(user.uid, user.kyc)}
                            >
                              Verify
                            </button>
                            &nbsp;
                            <button
                              className="btn btn-primary"
                              onClick={() => viewUser(user.uid)}
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

            <div className="card col-12">
              <h3 className="card-header">KYC VERIFIED USERS</h3>
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
                    {users
                      .filter((user) => user.kyc === true)
                      .map((user) => (
                        <tr>
                          <th scope="row">{user.uid}</th>
                          <td>{user.firstName + " " + user.lastName}</td>
                          <td>{user.aadharNumber}</td>
                          <td>
                            {user.kyc ? (
                              <p className="alert alert-success">Verified</p>
                            ) : (
                              <p className="alert alert-danger">Not Verified</p>
                            )}
                          </td>
                          <td>
                            <button
                              className="btn btn-success"
                              onClick={() => verifyUser(user.uid, user.kyc)}
                            >
                              Verify
                            </button>
                            &nbsp;
                            <button
                              className="btn btn-primary"
                              onClick={() => viewUser(user.uid)}
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
