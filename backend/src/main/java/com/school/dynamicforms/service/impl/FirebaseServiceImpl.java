package com.school.dynamicforms.service.impl;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.school.dynamicforms.model.FormField;
import com.school.dynamicforms.model.FormResponse;
import com.school.dynamicforms.service.FirebaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseServiceImpl implements FirebaseService {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseServiceImpl.class);

    @Value("${firebase.config.path}")
    private Resource firebaseConfigPath;

    private Firestore firestore;

    @PostConstruct
    public void init() {
        initializeFirebase();
    }

    @Override
    public void initializeFirebase() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(firebaseConfigPath.getInputStream()))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            
            firestore = FirestoreClient.getFirestore();
            logger.info("Firebase Firestore has been initialized successfully");
        } catch (IOException e) {
            logger.error("Error initializing Firebase: {}", e.getMessage());
        }
    }

    @Override
    public FormResponse getFormConfigByRole(String role) {
        if (firestore == null) {
            logger.warn("Firebase Firestore not initialized");
            return null;
        }

        try {
            // Get document from Firestore
            DocumentReference docRef = firestore.collection("forms").document(role);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            
            if (document.exists()) {
                try {
                    // Get form data
                    String formTitle = document.getString("formTitle");
                    String formDescription = document.getString("formDescription");
                    
                    List<FormField> fields = new ArrayList<>();
                    List<Map<String, Object>> fieldsList = (List<Map<String, Object>>) document.get("fields");
                    
                    if (fieldsList != null) {
                        for (Map<String, Object> fieldMap : fieldsList) {
                            FormField field = new FormField();
                            field.setId((String) fieldMap.get("id"));
                            field.setLabel((String) fieldMap.get("label"));
                            field.setType((String) fieldMap.get("type"));
                            field.setRequired((Boolean) fieldMap.get("required"));
                            field.setPlaceholder((String) fieldMap.get("placeholder"));
                            field.setDefaultValue((String) fieldMap.get("defaultValue"));
                            
                            // Handle options for select, radio, checkbox fields
                            if (fieldMap.containsKey("options")) {
                                List<String> optionsList = (List<String>) fieldMap.get("options");
                                if (optionsList != null) {
                                    field.setOptions(optionsList.toArray(new String[0]));
                                }
                            }
                            
                            fields.add(field);
                        }
                    }
                    
                    return new FormResponse(formTitle, formDescription, fields);
                } catch (Exception e) {
                    logger.error("Error parsing form data: {}", e.getMessage());
                    return null;
                }
            } else {
                logger.warn("No form configuration found for role: {}", role);
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error getting form from Firestore: {}", e.getMessage());
            return null;
        }
    }
}
