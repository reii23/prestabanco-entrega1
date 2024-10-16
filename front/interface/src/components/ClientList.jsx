import React, { useEffect, useState } from "react";
import { Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";
import clientService from "../services/client.service";
import { useNavigate } from "react-router-dom";

const ClientList = () => {
  const [clients, setClients] = useState([]);
  const navigate = useNavigate();

  // Obtener lista de clientes desde el backend
  const fetchClients = async () => {
    try {
      const response = await clientService.getAllClients();
      setClients(response.data);
    } catch (error) {
      console.error("Error getting client list", error);
    }
  };

  useEffect(() => {
    fetchClients();
  }, []);

  const deleteClient = (id) => {
    clientService.deleteClient(id).then(() => {
      fetchClients();
    });
  };

  const editClient = (id) => {
    navigate(`/clients/edit/${id}`);
  };

  const addClient = () => {
    navigate("/clients/add");
  };

  return (
    <div>
      <h2>Lista de Clientes</h2>
      <Button variant="contained" color="primary" onClick={addClient}>
        Añadir Cliente
      </Button>
      <TableContainer component={Paper} sx={{ marginTop: "2rem" }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>RUT</TableCell>
              <TableCell>Nombre</TableCell>
              <TableCell>Edad</TableCell>
              <TableCell>Salario</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Acciones</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {clients.map((client) => (
              <TableRow key={client.id}>
                <TableCell>{client.id}</TableCell>
                <TableCell>{client.rut}</TableCell>
                <TableCell>{client.name}</TableCell>
                <TableCell>{client.age}</TableCell>
                <TableCell>{client.salary}</TableCell>
                <TableCell>{client.email}</TableCell>
                <TableCell>
                  <Button
                    variant="outlined"
                    color="primary"
                    onClick={() => editClient(client.id)}
                    sx={{ marginRight: "1rem" }}
                  >
                    Editar
                  </Button>
                  <Button
                    variant="outlined"
                    color="error"
                    onClick={() => deleteClient(client.id)}
                  >
                    Eliminar
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

export default ClientList;
