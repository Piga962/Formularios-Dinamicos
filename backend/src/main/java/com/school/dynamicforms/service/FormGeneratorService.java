package com.school.dynamicforms.service;

import com.school.dynamicforms.model.FormResponse;

/**
 * Abstract Method interface for form generation strategy
 */
public interface FormGeneratorService {
    
    /**
     * Generate form fields based on user role
     * @return FormResponse with fields for the specific user role
     */
    FormResponse generateForm();
    
    /**
     * Get form fields from Firebase
     * @return FormResponse with fields from Firebase configuration
     */
    FormResponse getFormFromFirebase();
}
