package com.school.dynamicforms.model;

import java.util.List;

public class FormResponse {
    private String formTitle;
    private String formDescription;
    private List<FormField> fields;

    public FormResponse() {
    }

    public FormResponse(String formTitle, String formDescription, List<FormField> fields) {
        this.formTitle = formTitle;
        this.formDescription = formDescription;
        this.fields = fields;
    }

    public static FormResponseBuilder builder() {
        return new FormResponseBuilder();
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public void setFormDescription(String formDescription) {
        this.formDescription = formDescription;
    }

    public List<FormField> getFields() {
        return fields;
    }

    public void setFields(List<FormField> fields) {
        this.fields = fields;
    }

    public static class FormResponseBuilder {
        private String formTitle;
        private String formDescription;
        private List<FormField> fields;

        FormResponseBuilder() {
        }

        public FormResponseBuilder formTitle(String formTitle) {
            this.formTitle = formTitle;
            return this;
        }

        public FormResponseBuilder formDescription(String formDescription) {
            this.formDescription = formDescription;
            return this;
        }

        public FormResponseBuilder fields(List<FormField> fields) {
            this.fields = fields;
            return this;
        }

        public FormResponse build() {
            return new FormResponse(formTitle, formDescription, fields);
        }
    }
}
