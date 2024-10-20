import React, { useEffect, useState } from "react";
import { Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material"; // icons
import loanService from "../services/loan.service";
import { useNavigate } from "react-router-dom";
import clientService from "../services/client.service"; 

const LoanList = () => {
  const [loans,setLoans] = useState([]);
  const [clients,setClients] = useState({});
  const navigate = useNavigate();

  const fetchLoans = async () => {
    try {const response = await loanService.getAllLoans();
      const loansData = response.data;

     // Obtain client data for each loan (by client id)
      const clientPromises = loansData.map((loan) => clientService.getClientById(loan.clientId) // use getClientById method from clientService to get client data
      );
      const clientResponses = await Promise.all(clientPromises);
      const clientData = clientResponses.reduce((acc, curr) => {
        acc[curr.data.id] = curr.data.rut;  // store client data in an object with the client id as key and the client rut as value
        return acc;
      }, {});

      setLoans(loansData);
      setClients(clientData);
    } catch (error) {
      console.error("Error getting the loan list", error);
    }
  };

  useEffect(() => {
    fetchLoans();}, []);

    // evaluate a loan request by id (TO DO: in proccess: change to a new view to evaluate)
  const evaluateLoan = async (id) => {
    try {
      const response = await loanService.evaluateLoan(id);
      alert(`Evaluation Completed: ${response.data}`);
      fetchLoans(); // refresh loan list after evaluation
    } catch (error) {
      console.error("Error in the loan evaluation", error);
    }
  };

  return (
    <div>
      <h2>Lista de Solicitudes de Crédito</h2>
      <Button variant="contained" color="primary" onClick={() => navigate("/loans/add")}>
        Agregar Nueva Solicitud
      </Button>
      <TableContainer component={Paper} sx={{ marginTop: "2rem" }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Cliente RUT</TableCell>
              <TableCell>Tipo de Préstamo</TableCell>
              <TableCell>Monto Solicitado</TableCell>
              <TableCell>Plazo (Años)</TableCell>
              <TableCell>Tasa de Interés</TableCell>
              <TableCell>Estado</TableCell>
              <TableCell>Acciones</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {loans.map((loan) => (
              <TableRow key={loan.idCreditRequest}>
                <TableCell>{loan.idCreditRequest}</TableCell>
                <TableCell>{clients[loan.clientId] || "Desconocido"}</TableCell>
                <TableCell>{loan.loanType}</TableCell>
                <TableCell>{loan.requestedAmount}</TableCell>
                <TableCell>{loan.termYears}</TableCell>
                <TableCell>{loan.interestRate}</TableCell>
                <TableCell>{loan.status}</TableCell>
                <TableCell>
                  <Button
                    variant="outlined"
                    color="primary"
                    onClick={() => navigate(`/loans/${loan.idCreditRequest}`)}
                    sx={{ marginRight: "1rem" }}
                  >
                    Ver Detalles
                  </Button>
                  <Button
                    variant="outlined"
                    color="secondary"
                    onClick={() => evaluateLoan(loan.idCreditRequest)}
                  >
                    Evaluar
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default LoanList;
