package com.school.dynamicforms.service.impl;

import com.school.dynamicforms.model.FormField;
import com.school.dynamicforms.model.FormResponse;
import com.school.dynamicforms.service.FormGeneratorService;
import com.school.dynamicforms.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GuestFormGeneratorService implements FormGeneratorService {

    private final FirebaseService firebaseService;
    
    @Autowired
    public GuestFormGeneratorService(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }
    
    @Override
    public FormResponse generateForm() {
        // Check if we have form configuration in Firebase first
        FormResponse firebaseForm = getFormFromFirebase();
        if (firebaseForm != null) {
            return firebaseForm;
        }
        
        // Default fallback implementation if Firebase config is not available
        List<FormField> fields = Arrays.asList(
            FormField.builder()
                .id("guestField1")
                .label("Full Name")
                .type("text")
                .required(true)
                .placeholder("Enter your full name")
                .build(),
            FormField.builder()
                .id("guestField2")
                .label("Email")
                .type("email")
                .required(true)
                .placeholder("Enter your email address")
                .build(),
            FormField.builder()
                .id("guestField3")
                .label("Reason for Visit")
                .type("textarea")
                .required(true)
                .placeholder("Please tell us why you're here today")
                .build()
        );
        
        return FormResponse.builder()
                .formTitle("Guest Registration Form")
                .formDescription("Please provide your information to proceed as a guest")
                .fields(fields)
                .build();
    }
    
    @Override
    public FormResponse getFormFromFirebase() {
        return firebaseService.getFormConfigByRole("guest");
    }
}
