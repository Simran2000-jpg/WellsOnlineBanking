import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import AdminServices from "../services/AdminServices";
import { Context } from "../context/Context";

const UserDetails = () => {
  const { userId, dispatch } = useContext(Context);

  const navigate = useNavigate();
  const id = useParams().id;
  const [user, setUser] = useState({});

  const fetchUser = async () => {
    AdminServices.fetchUserById(id).then((res) => {
      console.log(res);
      setUser(res);
      fetchAccounts();
    });
  };

  const fetchAccounts = async () => {
    AdminServices.fetchAccountByUser(id).then((res) => {
      console.log(res);

      setUser((user) => ({ ...user, account: res }));
      console.log(user);
    });
  };

  const changeActiveStatusAccount = async (accountNo) => {
    AdminServices.changeAccountStatus(accountNo).then((res) => {
      console.log(res);
      fetchAccounts();
    });
  };
  useEffect(() => {
    if (userId !== "admin") {
      dispatch({
        type: "LOGOUT",
      });
    }

    fetchUser();
  }, [userId]);
  return (
    <div className="form-bg my-5 mx-auto container">
      <div className="row justify-content-center">
        <div className="col-md-6 col-12 ">
          <div className="form-container">
            <h2 className="title">USER DETAILS</h2>
            <table className="table table-striped">
              <tbody>
                {user && (
                  <>
                    <tr>
                      <td className="property">User ID</td>
                      {user.uid && <td className="value">{user.uid}</td>}
                    </tr>
                    <tr>
                      <td className="property">First Name</td>
                      {user.firstName && (
                        <td className="value">{user.firstName}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">Last Name</td>
                      {user.lastName && (
                        <td className="value">{user.lastName}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">Email</td>
                      {user.emailId && (
                        <td className="value">{user.emailId}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">Phone Number</td>
                      {user.phoneNumber && (
                        <td className="value">{user.phoneNumber}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">PAN Number</td>
                      {user.panNumber && (
                        <td className="value">{user.panNumber}</td>
                      )}
                    </tr>

                    <tr>
                      <td className="property">Aadhar Number</td>
                      {user.aadharNumber && (
                        <td className="value">{user.aadharNumber}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">Date of Birth</td>
                      {user.dob && <td className="value">{user.dob}</td>}
                    </tr>
                    <tr>
                      <td className="property">Occupation</td>
                      {user.occupation && (
                        <td className="value">{user.occupation}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">Gender</td>
                      {user.gender && <td className="value">{user.gender}</td>}
                    </tr>
                    <tr>
                      <td className="property">KYC</td>
                      <td className="value">
                        {user.kyc ? (
                          <button className="btn btn-success" disabled>
                            Verified
                          </button>
                        ) : (
                          <button className="btn btn-danger" disabled>
                            Not Verified
                          </button>
                        )}
                      </td>
                    </tr>
                    <tr>
                      <td className="property">Permanent Address</td>
                      {user.permanentAddress && (
                        <td className="value">
                          {user.permanentAddress.address}
                        </td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">City</td>
                      {user.permanentAddress && (
                        <td className="value">{user.permanentAddress.city}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">State</td>
                      {user.permanentAddress && (
                        <td className="value">{user.permanentAddress.state}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">Pincode</td>

                      {user.permanentAddress && (
                        <td className="value">
                          {user.permanentAddress.pincode}
                        </td>
                      )}
                    </tr>
                    <tr>
                      <td className="property text-center" colSpan={2}>
                        <h3>Accounts</h3>
                      </td>
                    </tr>
                    <tr>
                      <td colSpan={2}>
                        {user.account &&
                          user.account.map((account) => (
                            <div
                              className="card text-center"
                              key={account.accountNo}
                            >
                              <div className="card-body">
                                <h5 className="card-title">
                                  Account Number: {account.accountNo}
                                </h5>
                                {account.isActive ? (
                                  <button
                                    className="btn btn-success"
                                    onClick={() => {
                                      changeActiveStatusAccount(
                                        account.accountNo
                                      );
                                    }}
                                  >
                                    Active
                                  </button>
                                ) : (
                                  <button
                                    className="btn btn-danger"
                                    onClick={() => {
                                      changeActiveStatusAccount(
                                        account.accountNo
                                      );
                                    }}
                                  >
                                    Inactive
                                  </button>
                                )}
                                <p className="card-text">
                                  IFSC Code: {account.ifscCode}
                                </p>
                                <p className="card-text">
                                  Balance: {account.balance}
                                </p>
                              </div>
                            </div>
                          ))}
                      </td>
                    </tr>
                  </>
                )}
              </tbody>
            </table>

            <button
              className="btn btn-primary"
              onClick={() => navigate("/admin")}
            >
              Back
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserDetails;
