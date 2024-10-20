import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import loanService from '../services/loan.service';
import { Button, Box, TextField, Typography, Paper } from '@mui/material';


// TO DO: MAKE A VIEW REQUEST: EVALUATE CREDIT REQUEST


const EvaluateCreditRequest = () => {
  const { id } = useParams();
  const [creditRequest, setCreditRequest] = useState(null);
  const [paymentToIncomeRatio, setPaymentToIncomeRatio] = useState(0);
  const [debtToIncomeRatio, setDebtToIncomeRatio] = useState(0);
  const [creditHistory, setCreditHistory] = useState('');
  const [employmentStability, setEmploymentStability] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    loanService.getAllLoans(id).then(response => {
      setCreditRequest(response.data);
      calculateRatios(response.data);
    });
  }, [id]);

  const calculateRatios = (data) => {
    const { requestedAmount, termYears, interestRate, expenses, salary } = data;
    const monthlyPayment = (requestedAmount * (interestRate / 12 / 100)) / 
        (1 - Math.pow(1 + (interestRate / 12 / 100), -termYears * 12));
    
    setPaymentToIncomeRatio((monthlyPayment / salary) * 100);
    setDebtToIncomeRatio(((monthlyPayment + expenses) / salary) * 100);
  };

  const handleEvaluate = () => {
    const evaluationData = {
      creditHistory,
      employmentStability,
      paymentToIncomeRatio,
      debtToIncomeRatio
    };
    
    loanService.evaluateLoan(id, evaluationData).then(response => {
      alert('Evaluación completada: ' + response.data);
      navigate('/loans');
    }).catch(err => {
      console.error('Error al evaluar la solicitud:', err);
    });
  };

  return (
    creditRequest && (
      <Paper sx={{ padding: '20px', margin: '20px auto', maxWidth: '800px' }}>
        <Typography variant="h4">Evaluar Solicitud de Crédito #{id}</Typography>
        <Box component="form" sx={{ display: 'flex', flexDirection: 'column', gap: 2, marginTop: '20px' }}>
          <Typography variant="h6">Información del Cliente</Typography>
          <TextField label="Monto Solicitado" value={creditRequest.requestedAmount} disabled />
          <TextField label="Plazo (años)" value={creditRequest.termYears} disabled />
          <TextField label="Tasa de Interés (%)" value={creditRequest.interestRate} disabled />
          <TextField label="Relación Cuota/Ingreso (%)" value={paymentToIncomeRatio.toFixed(2)} disabled />
          <TextField label="Relación Deuda/Ingreso (%)" value={debtToIncomeRatio.toFixed(2)} disabled />

          <Typography variant="h6">Evaluación Manual del Ejecutivo</Typography>
          <TextField
            select
            label="Historial Crediticio"
            value={creditHistory}
            onChange={(e) => setCreditHistory(e.target.value)}
            slotProps={{
              native: true,
            }}
          >
            <option value="" disabled>Seleccionar...</option>
            <option value="positive">Sin morosidad</option>
            <option value="negative">Con morosidad</option>
          </TextField>

          <TextField
            select
            label="Estabilidad Laboral"
            value={employmentStability}
            onChange={(e) => setEmploymentStability(e.target.value)}
            slotProps={{
              native: true,
            }}
          >
            <option value="" disabled>Seleccionar...</option>
            <option value="stable">Estable</option>
            <option value="unstable">Inestable</option>
          </TextField>

          <Button variant="contained" color="primary" onClick={handleEvaluate}>
            Evaluar Solicitud
          </Button>
        </Box>
      </Paper>
    )
  );
};

export default EvaluateCreditRequest;
