import React, { useState } from 'react';
import { 
    Box, 
    Button, 
    FormControl, 
    FormControlLabel, 
    Paper, 
    Radio, 
    RadioGroup, 
    Typography 
} from '@mui/material';
import { useAuth } from '../context/AuthContext';

const Login = () => {
    const [userRole, setUserRole] = useState('guest');
    const { login } = useAuth();

    const handleRoleChange = (e) => {
        setUserRole(e.target.value);
    };

    const handleLogin = (e) => {
        e.preventDefault();
        login(userRole);
    };

    return (
        <Paper elevation={3} sx={{ p: 3, maxWidth: 400, mx: 'auto', my: 4 }}>
            <Typography variant="h5" component="h2" gutterBottom align="center">
                Select User Role
            </Typography>
            
            <Box component="form" onSubmit={handleLogin} sx={{ mt: 2 }}>
                <FormControl component="fieldset" fullWidth margin="normal">
                    <RadioGroup
                        aria-label="user-role"
                        name="user-role"
                        value={userRole}
                        onChange={handleRoleChange}
                    >
                        <FormControlLabel 
                            value="admin" 
                            control={<Radio />} 
                            label="Admin" 
                        />
                        <FormControlLabel 
                            value="guest" 
                            control={<Radio />} 
                            label="Guest" 
                        />
                    </RadioGroup>
                </FormControl>
                
                <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    fullWidth
                    sx={{ mt: 3, mb: 2 }}
                >
                    Login as {userRole.charAt(0).toUpperCase() + userRole.slice(1)}
                </Button>
            </Box>
        </Paper>
    );
};

export default Login;
