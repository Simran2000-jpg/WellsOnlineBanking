import axios from "axios";

const baseURL = "http://localhost:8085";

export default class UserService {
  static async getUserDetails(userId) {
    try {
      const response = await axios.get(baseURL + `/users/${userId}`);
      return response.data;
    } catch (error) {
      return error;
    }
  }
}