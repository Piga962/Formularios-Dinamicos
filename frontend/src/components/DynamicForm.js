import React, { useState } from 'react';
import { 
    Box, 
    TextField, 
    Button, 
    Typography, 
    Paper, 
    FormControl, 
    InputLabel, 
    Select, 
    MenuItem, 
    FormHelperText,
    Checkbox,
    FormControlLabel,
    Radio,
    RadioGroup,
    TextareaAutosize
} from '@mui/material';

// Dynamic Form Component that renders fields based on configuration
const DynamicForm = ({ formConfig, onSubmit }) => {
    // Initialize form state based on field configuration
    const initFormState = () => {
        const initialState = {};
        if (formConfig?.fields) {
            formConfig.fields.forEach(field => {
                initialState[field.id] = field.defaultValue || '';
            });
        }
        return initialState;
    };

    const [formValues, setFormValues] = useState(initFormState);
    const [errors, setErrors] = useState({});

    // Update form state when input changes
    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormValues(prev => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value
        }));
        
        // Clear any error when the field is modified
        if (errors[name]) {
            setErrors(prev => ({ ...prev, [name]: null }));
        }
    };

    // Validate the form before submission
    const validateForm = () => {
        const newErrors = {};
        let isValid = true;
        
        formConfig.fields.forEach(field => {
            if (field.required && !formValues[field.id]) {
                newErrors[field.id] = 'This field is required';
                isValid = false;
            }
            
            // Add more validation as needed (email, numbers, etc.)
            if (field.type === 'email' && formValues[field.id] && 
                !/\S+@\S+\.\S+/.test(formValues[field.id])) {
                newErrors[field.id] = 'Please enter a valid email address';
                isValid = false;
            }
        });
        
        setErrors(newErrors);
        return isValid;
    };

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();
        if (validateForm()) {
            onSubmit(formValues);
        }
    };

    // Render different field types based on configuration
    const renderField = (field) => {
        const { id, label, type, required, placeholder, options } = field;
        
        switch (type) {
            case 'text':
            case 'email':
            case 'password':
            case 'number':
                return (
                    <TextField
                        key={id}
                        id={id}
                        name={id}
                        label={label}
                        type={type}
                        value={formValues[id] || ''}
                        onChange={handleChange}
                        fullWidth
                        margin="normal"
                        required={required}
                        placeholder={placeholder}
                        error={!!errors[id]}
                        helperText={errors[id]}
                    />
                );
                
            case 'textarea':
                return (
                    <FormControl key={id} fullWidth margin="normal" error={!!errors[id]}>
                        <InputLabel shrink htmlFor={id}>{label}</InputLabel>
                        <TextareaAutosize
                            id={id}
                            name={id}
                            value={formValues[id] || ''}
                            onChange={handleChange}
                            minRows={4}
                            placeholder={placeholder}
                            style={{ 
                                width: '100%', 
                                padding: '8px', 
                                marginTop: '16px',
                                fontFamily: 'inherit' 
                            }}
                        />
                        {errors[id] && <FormHelperText>{errors[id]}</FormHelperText>}
                    </FormControl>
                );
                
            case 'select':
                return (
                    <FormControl key={id} fullWidth margin="normal" required={required} error={!!errors[id]}>
                        <InputLabel id={`${id}-label`}>{label}</InputLabel>
                        <Select
                            labelId={`${id}-label`}
                            id={id}
                            name={id}
                            value={formValues[id] || ''}
                            onChange={handleChange}
                            label={label}
                        >
                            {options.map((option) => (
                                <MenuItem key={option} value={option}>
                                    {option}
                                </MenuItem>
                            ))}
                        </Select>
                        {errors[id] && <FormHelperText>{errors[id]}</FormHelperText>}
                    </FormControl>
                );
                
            case 'checkbox':
                return (
                    <FormControl key={id} fullWidth margin="normal" error={!!errors[id]}>
                        <FormControlLabel
                            control={
                                <Checkbox
                                    id={id}
                                    name={id}
                                    checked={!!formValues[id]}
                                    onChange={handleChange}
                                />
                            }
                            label={label}
                        />
                        {errors[id] && <FormHelperText>{errors[id]}</FormHelperText>}
                    </FormControl>
                );
                
            case 'radio':
                return (
                    <FormControl key={id} fullWidth margin="normal" required={required} error={!!errors[id]}>
                        <InputLabel shrink>{label}</InputLabel>
                        <RadioGroup
                            name={id}
                            value={formValues[id] || ''}
                            onChange={handleChange}
                        >
                            {options.map((option) => (
                                <FormControlLabel
                                    key={option}
                                    value={option}
                                    control={<Radio />}
                                    label={option}
                                />
                            ))}
                        </RadioGroup>
                        {errors[id] && <FormHelperText>{errors[id]}</FormHelperText>}
                    </FormControl>
                );
                
            default:
                return (
                    <Typography key={id} color="error">
                        Unknown field type: {type}
                    </Typography>
                );
        }
    };

    if (!formConfig) {
        return <Typography>Loading form configuration...</Typography>;
    }

    return (
        <Paper elevation={3} sx={{ p: 3, maxWidth: 600, mx: 'auto', my: 4 }}>
            <Typography variant="h5" component="h2" gutterBottom>
                {formConfig.formTitle}
            </Typography>
            
            {formConfig.formDescription && (
                <Typography variant="body2" color="textSecondary" sx={{ mb: 3 }}>
                    {formConfig.formDescription}
                </Typography>
            )}
            
            <Box component="form" onSubmit={handleSubmit} noValidate>
                {formConfig.fields.map(field => renderField(field))}
                
                <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    fullWidth
                    sx={{ mt: 3, mb: 2 }}
                >
                    Submit
                </Button>
            </Box>
        </Paper>
    );
};

export default DynamicForm;
