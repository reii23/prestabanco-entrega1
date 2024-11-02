import axios from "axios";

const API_URL = "http://prestabanco-app.brazilsouth.cloudapp.azure.com:80/api/CreditSimulation/";

const simulateCredit = (creditSimulation) => {
    return axios.post(API_URL, creditSimulation);
};

export default {
    simulateCredit,
};
