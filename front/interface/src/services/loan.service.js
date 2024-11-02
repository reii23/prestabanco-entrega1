import axios from 'axios';

const API_URL = "http://prestabanco-app.brazilsouth.cloudapp.azure.com:80/api/v1/CreditRequest/";

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

// delete a loan request by id
const deleteCreditRequestById = (id) => {
  return axios.delete(`${API_URL}${id}`);
};


export default {
  getAllLoans,
  getLoanById,
  evaluateLoan, 
  deleteCreditRequestById,
};
