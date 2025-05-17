package com.school.dynamicforms.model;

public class FormField {
    private String id;
    private String label;
    private String type;
    private boolean required;
    private String placeholder;
    private String defaultValue;
    private String[] options; // For select, radio, checkbox fields

    public FormField() {
    }

    public FormField(String id, String label, String type, boolean required, String placeholder, String defaultValue, String[] options) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.required = required;
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
        this.options = options;
    }

    public static FormFieldBuilder builder() {
        return new FormFieldBuilder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public static class FormFieldBuilder {
        private String id;
        private String label;
        private String type;
        private boolean required;
        private String placeholder;
        private String defaultValue;
        private String[] options;

        FormFieldBuilder() {
        }

        public FormFieldBuilder id(String id) {
            this.id = id;
            return this;
        }

        public FormFieldBuilder label(String label) {
            this.label = label;
            return this;
        }

        public FormFieldBuilder type(String type) {
            this.type = type;
            return this;
        }

        public FormFieldBuilder required(boolean required) {
            this.required = required;
            return this;
        }

        public FormFieldBuilder placeholder(String placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public FormFieldBuilder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public FormFieldBuilder options(String[] options) {
            this.options = options;
            return this;
        }

        public FormField build() {
            return new FormField(id, label, type, required, placeholder, defaultValue, options);
        }
    }
}
