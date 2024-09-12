package com.nubolerta.tech.constants;

import java.lang.reflect.Field;

public interface SystemConstants {
  String COLUMN_NAME = "COLUMN_NAME";
  String COLUMN_TYPE = "TYPE_NAME";
  String ID_COLUMN = "id";
  String GATEWAY_USER_TABLE_NAME = "GATEWAY_USER";
  String TEXT = "TEXT";
  String DB_TEXT = "CHARACTER VARYING";

  public static void setDynamicField(Object obj, String fieldName, Object value) {
    try {
        // Get the field from the object's class
        Field field = obj.getClass().getDeclaredField(fieldName);

        // Set the field accessible (in case it's private)
        field.setAccessible(true);

        // Set the value of the field
        field.set(obj, value);

    } catch (NoSuchFieldException | IllegalAccessException e) {
        e.printStackTrace();
    }
}
}
