import React, { useState } from 'react';
import { Box, TextField, Button, Typography } from '@mui/material';
import creditRequestService from '../services/creditRequest.service';

const CreditStatusView = () => {
    const [requestId, setRequestId] = useState('');
    const [status, setStatus] = useState('');
    const [message, setMessage] = useState('');

    // manage the status check of a credit request
    const handleStatusCheck = async (e) => {
        e.preventDefault();
        try {
            const response = await creditRequestService.getCreditRequestStatus(requestId);
            setStatus(response.data); // set the status of the request
            setMessage('');
        } catch (error) {
            setMessage('No se encontró el estado de la solicitud para el ID entregado');
            setStatus('');
        }
    };

    return (
        <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', mt: 4 }}>
            <h1>Seguimiento del estado de una solicitud</h1>
            <h4>Ingrese el id de la solicitud para obtener el estado en que se encuentra</h4>
            <form onSubmit={handleStatusCheck} style={{ width: '50%', marginTop: '2rem' }}>
                <TextField
                    label="Ingrese el id de la solicitud"
                    name="requestId"
                    value={requestId}
                    onChange={(e) => setRequestId(e.target.value)}
                    fullWidth
                    margin="normal"
                />
                <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
                    Buscar Estado
                </Button>
            </form>

            {message && (
                <Typography variant="h6" color="error" sx={{ mt: 2 }}>
                    {message}
                </Typography>
            )}

            {status && (
                <Typography variant="h6" color="primary" sx={{ mt: 4 }}>
                    El estado de la solicitud es: {status}
                </Typography>
            )}
        </Box>
    );
};

export default CreditStatusView;
