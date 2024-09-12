package com.nubolerta.tech.dto;

public class UserFieldValue {
  String fieldName;
  String fieldValue;
  public UserFieldValue(String fieldName, String fieldValue) {
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;

  }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
