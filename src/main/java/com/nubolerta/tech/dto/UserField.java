package com.nubolerta.tech.dto;

public class UserField {
  String fieldName;
  String fieldType;

  public UserField(String fieldName, String fieldType) {
    this.fieldName = fieldName;
    this.fieldType = fieldType;

  }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
  
}
