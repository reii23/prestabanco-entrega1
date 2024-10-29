import axios from 'axios';

const API_URL = "http://localhost:8080/api/v1/CreditRequest/";

// obtain all loans from the API
const getAllLoans = () => {
  return axios.get(API_URL);
};

// obtain a loan by id
const getLoanById = (id) => {
  return axios.get(`${API_URL}${id}`);
};

// evaluate a loan request by id
const evaluateLoan = (id, evaluationData) => {
  return axios.post(`${API_URL}evaluate/${id}`, evaluationData);
};

export default {
  getAllLoans,
  getLoanById,
  evaluateLoan, 
};
