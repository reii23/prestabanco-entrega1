import axios from 'axios';

const API_URL = "prestabanco-app.brazilsouth.cloudapp.azure.com:80/api/v1/LoanType/";

// obtain all loan types (first dwelling, second dwelling, etc)
const getAllLoanTypes = () => {
    return axios.get(API_URL);
};

export default {
    getAllLoanTypes,
};
