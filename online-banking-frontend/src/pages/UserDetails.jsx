import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Users } from "../data/Users";

const UserDetails = () => {
  const navigate = useNavigate();
  const id = useParams().id;
  const [user, setUser] = useState({});

  const fetchUser = async () => {
    let result = Users.filter((user) => user.userId == id);
    console.log(result[0]);
    return result[0];
  };
  useEffect(() => {
    console.log(id);
    fetchUser().then((data) => setUser(data));
  }, []);
  return (
    <div className="form-bg my-5 mx-auto container">
      <div className="row justify-content-center">
        <div className="col-md-offset-3 col-12 col-sm-offset-2">
          <div className="form-container">
            <h2 className="title">USER DETAILS</h2>
            <table className="table table-striped">
              <tbody>
                {user && (
                  <>
                    <tr>
                      <td className="property">User ID</td>
                      {user.userId && <td className="value">{user.userId}</td>}
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
                      {user.address && (
                        <td className="value">
                          {user.address.permanentAddress}
                        </td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">City</td>
                      {user.address && (
                        <td className="value">{user.address.city}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">State</td>
                      {user.address && (
                        <td className="value">{user.address.state}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">Pincode</td>

                      {user.address && (
                        <td className="value">{user.address.pincode}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">Account Number</td>
                      {user.account && (
                        <td className="value">{user.account.accountNumber}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">IFSC Code</td>
                      {user.account && (
                        <td className="value">{user.account.ifscCode}</td>
                      )}
                    </tr>
                    <tr>
                      <td className="property">Balance</td>
                      {user.account && (
                        <td className="value">{user.account.balance}</td>
                      )}
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
