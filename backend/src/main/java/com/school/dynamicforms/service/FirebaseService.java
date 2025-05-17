package com.school.dynamicforms.service;

import com.school.dynamicforms.model.FormResponse;

public interface FirebaseService {
    
    /**
     * Get form configuration by user role from Firebase
     * @param role User role (admin, guest)
     * @return FormResponse with fields from Firebase configuration
     */
    FormResponse getFormConfigByRole(String role);
    
    /**
     * Initialize Firebase connection
     */
    void initializeFirebase();
}
