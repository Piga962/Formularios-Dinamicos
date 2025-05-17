import React from 'react';
import { Container, CssBaseline, Typography, Box } from '@mui/material';
import './App.css';
import { AuthProvider, useAuth } from './context/AuthContext';
import Login from './components/Login';
import FormContainer from './components/FormContainer';

// Main App Content
const AppContent = () => {
  const { currentUser } = useAuth();
  
  return (
    <Container component="main">
      <CssBaseline />
      <Box
        sx={{
          marginTop: 4,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <Typography component="h1" variant="h4" gutterBottom>
          Dynamic Form System
        </Typography>
        
        {currentUser ? <FormContainer /> : <Login />}
      </Box>
    </Container>
  );
};

// Main App with Auth Provider wrapper
function App() {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
}

export default App;
