import axios from "axios";

const baseURL = "http://localhost:8085/transactions";

export default class TransactionService {
    static async initiateTransaction(fromAccount, toAccount, transactionDetail) {
        try {
            const response = await axios.post(baseURL + `/${fromAccount}/${toAccount}`, transactionDetail,
            {
              headers: { "Content-Type": "multipart/form-data" },
            });
            return response;
          } catch (error) {
            return error;
          }
    }
}