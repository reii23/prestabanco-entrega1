import axios from 'axios';

const API_URL = "http://localhost:8080/api/v1/CreditRequest/";

// get a client by rut to check if it exists
const getClientByRut = (rut) => {
    return axios.get(`http://localhost:8080/api/v1/clients/rut/${rut}`);
};

// send a credit request to the API (content is multipart/form-data because it includes a files in PDF format)
const createCreditRequest = (formData) => {
    return axios.post(API_URL, formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
};

const getCreditRequestStatus = (id) => {
    return axios.get(`${API_URL}${id}/status`);
};


export default {
    getClientByRut,
    createCreditRequest,
    getCreditRequestStatus,
};
