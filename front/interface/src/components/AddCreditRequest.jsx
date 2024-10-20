import React, { useState, useEffect } from 'react';
import { Box, TextField, Button, Typography, MenuItem, Select, InputLabel, FormControl } from '@mui/material';
import creditRequestService from '../services/creditRequest.service';
import loanTypeService from '../services/loanType.service';

const AddCreditRequest = () => {
    const [rut, setRut] = useState('');
    const [client, setClient] = useState(null);
    const [loanTypes, setLoanTypes] = useState([]);
    const [creditData, setCreditData] = useState({
        clientId: '',
        expenses: '',
        loanTypeId: '',
        loanType: '',
        requestedAmount: '',
        termYears: '',
        interestRate: ''
    });
    const [incomeProofPdf, setIncomeProofPdf] = useState(null);
    const [propertyValuationPdf, setPropertyValuationPdf] = useState(null);
    const [creditHistoryPdf, setCreditHistoryPdf] = useState(null);
    const [additionalFile, setAdditionalFile] = useState(null);
    const [message, setMessage] = useState('');

    // obtain the list of loan types 
    useEffect(() => {
        const fetchLoanTypes = async () => {
            try {
                const response = await loanTypeService.getAllLoanTypes();
                setLoanTypes(response.data); // save the list of loan types in the state
            } catch (error) {
                console.error("Error getting loan types", error);
            }
        };
        fetchLoanTypes();
    }, []);

    /// search for a client by rut -> to verify if the client exists to create a credit request
    const handleRutSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await creditRequestService.getClientByRut(rut);
            setClient(response.data); // obtain the client data (id) 
            setCreditData({ ...creditData, clientId: response.data.id });
            setMessage('');
        } catch (error) {
            setMessage('Client not found');
            setClient(null);
        }
    };

    // change the value of the fields in the form (expenses, loan type, requested amount, term in years, interest rate) 
    const handleChange = (e) => {
        const { name, value } = e.target;
    
        if (name === 'loanTypeId') {
            const selectedLoanType = loanTypes.find(loan => loan.id === parseInt(value));
            
            // reset the files when changing the loan type (to avoid sending the wrong files)
            setIncomeProofPdf(null);
            setPropertyValuationPdf(null);
            setCreditHistoryPdf(null);
            setAdditionalFile(null);
            setCreditData({
                ...creditData,
                loanTypeId: parseInt(value),
                loanType: selectedLoanType?.name || ''
            });
        } else {
            setCreditData({ ...creditData, [name]: value });
        }
    };
    
    // change the file to upload (income proof, property valuation, credit history, additional file)
    const handleFileChange = (e, setFile) => {
        setFile(e.target.files[0]);
    };

    // render the fields to upload the required documents according to the selected loan type
    const renderDocumentFields = () => {
        switch (creditData.loanType) {
            case 'Primera Vivienda': // Usamos el nombre del préstamo
                return (
                    <>
                        <Typography variant="h6">Comprobante de Ingresos</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setIncomeProofPdf)} accept="application/pdf" />
    
                        <Typography variant="h6">Certificado de Avalúo</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setPropertyValuationPdf)} accept="application/pdf" />
    
                        <Typography variant="h6">Historial Crediticio</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setCreditHistoryPdf)} accept="application/pdf" />
                    </>
                );
            case 'Segunda Vivienda': // Usamos el nombre del préstamo
                return (
                    <>
                        <Typography variant="h6">Comprobante de Ingresos</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setIncomeProofPdf)} accept="application/pdf" />
    
                        <Typography variant="h6">Certificado de Avalúo</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setPropertyValuationPdf)} accept="application/pdf" />
    
                        <Typography variant="h6">Escritura de la Primera Vivienda</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setAdditionalFile)} accept="application/pdf" />
    
                        <Typography variant="h6">Historial Crediticio</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setCreditHistoryPdf)} accept="application/pdf" />
                    </>
                );
            case 'Propiedades Comerciales': // Usamos el nombre del préstamo
                return (
                    <>
                        <Typography variant="h6">Estado Financiero del Negocio</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setAdditionalFile)} accept="application/pdf" />
    
                        <Typography variant="h6">Comprobante de Ingresos</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setIncomeProofPdf)} accept="application/pdf" />
    
                        <Typography variant="h6">Certificado de Avalúo</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setPropertyValuationPdf)} accept="application/pdf" />
    
                        <Typography variant="h6">Plan de Negocios</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setCreditHistoryPdf)} accept="application/pdf" />
                    </>
                );
            case 'Remodelación': // Usamos el nombre del préstamo
                return (
                    <>
                        <Typography variant="h6">Comprobante de Ingresos</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setIncomeProofPdf)} accept="application/pdf" />
    
                        <Typography variant="h6">Presupuesto de la Remodelación</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setAdditionalFile)} accept="application/pdf" />
    
                        <Typography variant="h6">Certificado de Avalúo Actualizado</Typography>
                        <input type="file" onChange={(e) => handleFileChange(e, setPropertyValuationPdf)} accept="application/pdf" />
                    </>
                );
            default:
                return <Typography variant="h6">Por favor seleccione un tipo de préstamo para ver los documentos requeridos.</Typography>;
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('clientId', creditData.clientId.toString());
        formData.append('expenses', creditData.expenses.toString());
        formData.append('loanTypeId', creditData.loanTypeId.toString());
        formData.append('loanType', creditData.loanType);
        formData.append('requestedAmount', creditData.requestedAmount.toString());
        formData.append('termYears', creditData.termYears.toString());
        formData.append('interestRate', creditData.interestRate.toString());
        formData.append('status', 'in initial review');
        
        formData.append('incomeProofPdf', incomeProofPdf);
        formData.append('propertyValuationPdf', propertyValuationPdf);
        formData.append('creditHistoryPdf', creditHistoryPdf);
        if (additionalFile) {
            formData.append('renovationBudgetPdf', additionalFile);
        }

        try {
            const response = await creditRequestService.createCreditRequest(formData);
            console.log('Solicitud enviada exitosamente:', response.data);
            setMessage('Solicitud enviada exitosamente.');
        } catch (error) {
            console.error('Error al enviar la solicitud:', error);
            setMessage('Error al enviar la solicitud: ' + error.response?.data?.message || error.message);
        }
    };

    return (
        <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', mt: 4 }}>
            <Typography variant="h4">Solicitar Crédito</Typography>
            {!client ? (
                <form onSubmit={handleRutSubmit} style={{ width: '50%' }}>
                    <TextField
                        label="Ingrese su RUT"
                        name="rut"
                        value={rut}
                        onChange={(e) => setRut(e.target.value)}
                        fullWidth
                        margin="normal"
                    />
                    <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
                        Buscar Cliente
                    </Button>
                </form>
            ) : (
                <form onSubmit={handleSubmit} style={{ width: '50%', marginTop: '2rem' }}>
                    <TextField
                        label="Gastos del Cliente"
                        name="expenses"
                        value={creditData.expenses}
                        onChange={handleChange}
                        type="number"
                        fullWidth
                        margin="normal"
                    />
                    <FormControl fullWidth margin="normal">
                        <InputLabel id="loan-type-label">Tipo de Préstamo</InputLabel>
                        <Select
                            labelId="loan-type-label"
                            name="loanTypeId"
                            value={creditData.loanTypeId || ''} 
                            onChange={handleChange}
                            label="Tipo de Préstamo"
                        >
                            {loanTypes.map((loanType) => (
                                <MenuItem key={loanType.id} value={loanType.id}>
                                    {loanType.name}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    
                    <TextField
                        label="Monto Solicitado"
                        name="requestedAmount"
                        value={creditData.requestedAmount}
                        onChange={handleChange}
                        type="number"
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Plazo en Años"
                        name="termYears"
                        value={creditData.termYears}
                        onChange={handleChange}
                        type="number"
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Tasa de Interés"
                        name="interestRate"
                        value={creditData.interestRate}
                        onChange={handleChange}
                        type="number"
                        fullWidth
                        margin="normal"
                    />

                    <Typography variant="h5" sx={{ mt: 3 }}>
                        Documentos Requeridos
                    </Typography>
                    {renderDocumentFields()}

                    <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
                        Enviar Solicitud
                    </Button>
                </form>
            )}

            {message && (
                <Typography variant="h6" sx={{ mt: 4 }}>
                    {message}
                </Typography>
            )}
        </Box>
    );
};

export default AddCreditRequest;
