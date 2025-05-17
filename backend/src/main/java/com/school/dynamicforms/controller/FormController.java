package com.school.dynamicforms.controller;

import com.school.dynamicforms.model.FormResponse;
import com.school.dynamicforms.service.FormGeneratorFactory;
import com.school.dynamicforms.service.FormGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forms")
@CrossOrigin(origins = "*")
public class FormController {
    
    private final FormGeneratorFactory formGeneratorFactory;
    
    @Autowired
    public FormController(FormGeneratorFactory formGeneratorFactory) {
        this.formGeneratorFactory = formGeneratorFactory;
    }
    
    @GetMapping("/{userRole}")
    public ResponseEntity<FormResponse> getFormByUserRole(@PathVariable String userRole) {
        try {
            FormGeneratorService formGeneratorService = formGeneratorFactory.getFormGenerator(userRole);
            FormResponse formResponse = formGeneratorService.generateForm();
            return ResponseEntity.ok(formResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
