package com.nubolerta.tech.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nubolerta.tech.constants.SystemConstants;
import com.nubolerta.tech.dto.UserField;
import com.nubolerta.tech.dto.UserFieldValue;
import com.nubolerta.tech.entities.SystemField;
import com.nubolerta.tech.service.ColumnMetadataService;
import com.nubolerta.tech.service.SystemFieldService;

@RestController
@RequestMapping("/api/systems/{system}")
public class SystemsController {
  @Autowired
  private ColumnMetadataService columnMetadataService;
  @Autowired
  private SystemFieldService systemFieldService;

  @RequestMapping(value = "/form-fields", method = RequestMethod.GET)
  ResponseEntity<List<UserField>> getFormFields(@PathVariable("system") String system) throws Exception {
    List<UserField> userFields = columnMetadataService.getDatabaseColumnDetails(SystemConstants.GATEWAY_USER_TABLE_NAME);
    List<SystemField> dbFields = systemFieldService.getAllSystemFieldBySystemName(system);
    List<UserField> dbUserFields = dbFields.stream().map(field -> new UserField(field.getName(), SystemConstants.TEXT)).collect(Collectors.toList());
    List<UserField> allFields = new ArrayList<>();
    allFields.addAll(userFields);
    allFields.addAll(dbUserFields);
    return ResponseEntity.ok().body(allFields);
  }

  @RequestMapping(value = "/users", method = RequestMethod.POST)
  ResponseEntity<Void> saveUserFields(@PathVariable("system") String system, @RequestBody List<UserFieldValue> userFieldValues) throws Exception {
    systemFieldService.saveUserFields(userFieldValues, system);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}