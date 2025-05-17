import React, { useState, useEffect } from 'react';
import { Box, Typography, Button, CircularProgress, Alert } from '@mui/material';
import { useAuth } from '../context/AuthContext';
import FormService from '../services/FormService';
import DynamicForm from './DynamicForm';

const FormContainer = () => {
    const { currentUser, logout } = useAuth();
    const [formConfig, setFormConfig] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [submissionResult, setSubmissionResult] = useState(null);

    // Fetch form configuration based on user role
    useEffect(() => {
        const fetchFormConfig = async () => {
            try {
                setLoading(true);
                setError(null);
                
                if (!currentUser) {
                    setError('No user found');
                    setLoading(false);
                    return;
                }
                
                const config = await FormService.getFormByUserRole(currentUser.role);
                setFormConfig(config);
            } catch (err) {
                setError('Failed to load form configuration. Please try again.');
                console.error('Error fetching form config:', err);
            } finally {
                setLoading(false);
            }
        };

        fetchFormConfig();
    }, [currentUser]);

    // Handle form submission
    const handleSubmit = (formData) => {
        // In a real app, you would submit this data to your backend
        console.log('Form submitted:', formData);
        
        setSubmissionResult({
            success: true,
            message: 'Form submitted successfully!'
        });
        
        // Reset submission message after 5 seconds
        setTimeout(() => {
            setSubmissionResult(null);
        }, 5000);
    };

    // Show loading state
    if (loading) {
        return (
            <Box display="flex" justifyContent="center" alignItems="center" minHeight="300px">
                <CircularProgress />
            </Box>
        );
    }

    // Show error state
    if (error) {
        return (
            <Box sx={{ maxWidth: 600, mx: 'auto', my: 4 }}>
                <Alert severity="error">{error}</Alert>
                <Button 
                    variant="outlined" 
                    onClick={logout} 
                    sx={{ mt: 2 }}
                >
                    Back to Login
                </Button>
            </Box>
        );
    }

    return (
        <Box sx={{ maxWidth: 800, mx: 'auto', my: 4, p: 2 }}>
            <Box display="flex" justifyContent="space-between" alignItems="center" mb={4}>
                <Typography variant="h4">
                    Welcome, {currentUser.name}
                </Typography>
                <Button variant="outlined" color="secondary" onClick={logout}>
                    Logout
                </Button>
            </Box>
            
            {submissionResult && (
                <Alert 
                    severity={submissionResult.success ? "success" : "error"}
                    sx={{ mb: 3 }}
                >
                    {submissionResult.message}
                </Alert>
            )}
            
            <DynamicForm formConfig={formConfig} onSubmit={handleSubmit} />
        </Box>
    );
};

export default FormContainer;
