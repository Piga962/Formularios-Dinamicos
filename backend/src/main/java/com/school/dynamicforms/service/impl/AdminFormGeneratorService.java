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
public class AdminFormGeneratorService implements FormGeneratorService {

    private final FirebaseService firebaseService;
    
    @Autowired
    public AdminFormGeneratorService(FirebaseService firebaseService) {
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
                .id("adminField1")
                .label("Project Name")
                .type("text")
                .required(true)
                .placeholder("Enter project name")
                .build(),
            FormField.builder()
                .id("adminField2")
                .label("Budget Allocation")
                .type("number")
                .required(true)
                .placeholder("Enter budget")
                .build(),
            FormField.builder()
                .id("adminField3")
                .label("Access Level")
                .type("select")
                .required(true)
                .options(new String[]{"High", "Medium", "Low"})
                .build()
        );
        
        return FormResponse.builder()
                .formTitle("Admin Management Form")
                .formDescription("Form for administrative tasks and project management")
                .fields(fields)
                .build();
    }
    
    @Override
    public FormResponse getFormFromFirebase() {
        return firebaseService.getFormConfigByRole("admin");
    }
}
