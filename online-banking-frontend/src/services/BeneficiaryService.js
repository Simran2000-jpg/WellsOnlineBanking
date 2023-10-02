import axios from "axios";

const baseURL = "http://localhost:8085/beneficiaries";

export default class BeneficiaryService {
    static async addBeneficiary(userId, beneficiaryData) {
        try {
            const response = await axios.post(baseURL + `/${userId}`, beneficiaryData);
            return response;
          } catch (error) {
            return error;
          }
    }

    static async viewBeneficiary(userId) {
        try {
            const response = await axios.get(baseURL + `/${userId}`);
            return response;
        }
        catch (error) {
            return error;
        }
    }

    static async deleteBeneficiary(bid) {
        try {
            const response = await axios.delete(baseURL + `/${bid}`);
            return response;
        } catch (error) {
            return error;
        }
    }
}