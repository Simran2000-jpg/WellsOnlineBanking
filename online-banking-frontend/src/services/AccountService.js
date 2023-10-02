import axios from "axios";

const baseURL = "http://localhost:8085";

export default class AccountService {
    static async getAccountDetails(userId) {
        try {
            const response = await axios.get(baseURL + `/active/${userId}`);
            return response.data;
        }
        catch (error) {
            return error;
        }
    }

    static async getAllFromAccounts(userId) {
        try {
            const response = await axios.get(baseURL + `/active/${userId}`);
            return response;
        } 
        catch (error) {
            return error;
        }
    }
}