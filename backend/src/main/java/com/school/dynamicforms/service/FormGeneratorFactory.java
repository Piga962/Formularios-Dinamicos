package com.school.dynamicforms.service;

import com.school.dynamicforms.service.impl.AdminFormGeneratorService;
import com.school.dynamicforms.service.impl.GuestFormGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormGeneratorFactory {
    
    private final AdminFormGeneratorService adminFormGeneratorService;
    private final GuestFormGeneratorService guestFormGeneratorService;
    
    @Autowired
    public FormGeneratorFactory(AdminFormGeneratorService adminFormGeneratorService, 
                              GuestFormGeneratorService guestFormGeneratorService) {
        this.adminFormGeneratorService = adminFormGeneratorService;
        this.guestFormGeneratorService = guestFormGeneratorService;
    }
    
    /**
     * Factory method to get the appropriate form generator based on user role
     * @param userRole The role of the user (admin, guest)
     * @return FormGeneratorService implementation for the specified role
     */
    public FormGeneratorService getFormGenerator(String userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("User role cannot be null");
        }
        
        switch (userRole.toLowerCase()) {
            case "admin":
                return adminFormGeneratorService;
            case "guest":
                return guestFormGeneratorService;
            default:
                throw new IllegalArgumentException("Unknown user role: " + userRole);
        }
    }
}
