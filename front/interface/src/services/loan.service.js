import axios from 'axios';

const API_URL = "http://localhost:8080/api/v1/CreditRequest/";

// obtain all loans from the API
const getAllLoans = () => {
  return axios.get(API_URL);
};


// TO DO: MAKE THE EVALUATE CREDIT REQUEST FUNCTION (ACTUALLY IS AUTOMATHIC BUT WE CAN MAKE A BUTTON VIEW TO EVALUATE IT)
const evaluateLoan = (id) => {
    return axios.post(`http://localhost:8080/api/v1/CreditRequest/evaluate/${id}`);
};


export default {
  getAllLoans,
  evaluateLoan, 
};
