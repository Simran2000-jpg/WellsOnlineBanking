import axios from "axios";

const API_URL = "http://localhost:8085/";

const ADMIN_CREDENTIALS = {
  username: "admin",
  password: "f3rokq034!@#$qu2",
};

class AdminServices {
  static async fetchAllUsers() {
    const response = await axios.get(API_URL + "users", {
      auth: ADMIN_CREDENTIALS,
    });
    return response.data;
  }

  static async fetchUserById(id) {
    const response = await axios.get(API_URL + "users/" + id, {
      auth: ADMIN_CREDENTIALS,
    });
    return response.data;
  }

  static async kycVerifyUser(id, kyc) {
    console.log(id, kyc);
    const response = await axios.put(
      API_URL + "users/" + id + "/verify",
      {
        kyc: kyc,
      },
      {
        auth: ADMIN_CREDENTIALS,
      }
    );
    return response.data;
  }

  static async changeAccountStatus(id) {
    const response = await axios.put(
      API_URL + "account/" + id + "/active",
      null,
      {
        auth: ADMIN_CREDENTIALS,
      }
    );
    return response.data;
  }

  static async fetchAccountByUser(id) {
    const response = await axios.get(API_URL + "user/" + id + "/accounts");
    return response.data;
  }
}

export default AdminServices;
