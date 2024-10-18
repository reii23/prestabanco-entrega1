import axios from "axios";

const API_URL = "http://localhost:8080/api/CreditSimulation/";

const simulateCredit = (creditSimulation) => {
    return axios.post(API_URL, creditSimulation);
};

export default {
    simulateCredit,
};
