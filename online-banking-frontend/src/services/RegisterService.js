import axios from "axios";

const baseURL = "http://localhost:8085";

export default class RegisterService {
    static async registerInternetBanking(registrationData) {
        try {
            const response = await axios.put(baseURL + "/register", registrationData, {
                headers: { "Content-Type": "multipart/form-data" }
            });
            return response;
        }
        catch (error) {
            return error;
        }
    }
}