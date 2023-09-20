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
}

export default AdminServices;
