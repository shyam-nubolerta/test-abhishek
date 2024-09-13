package com.nubolerta.tech.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nubolerta.tech.constants.SystemConstants;
import com.nubolerta.tech.dto.UserField;
import com.nubolerta.tech.dto.UserFieldValue;
import com.nubolerta.tech.entities.GatewayUser;
import com.nubolerta.tech.entities.SystemField;
import com.nubolerta.tech.entities.SystemUserFieldValue;
import com.nubolerta.tech.repository.GatewayUserRepository;
import com.nubolerta.tech.repository.SystemFieldRepository;
import com.nubolerta.tech.repository.SystemRepository;
import com.nubolerta.tech.repository.SystemUserFieldValueRepository;

import jakarta.persistence.Column;
import jakarta.transaction.Transactional;

@Service
public class SystemFieldService {

  private static final Logger logger = LoggerFactory.getLogger(SystemFieldService.class);

  private SystemFieldRepository systemFieldRepository;
  
  private SystemRepository systemRepository;

  private ColumnMetadataService columnMetadataService;

  private GatewayUserRepository gatewayUserRepository;

  private SystemUserFieldValueRepository systemUserFieldValueRepository;

  @Autowired
  public SystemFieldService(SystemFieldRepository systemFieldRepository, SystemRepository systemRepository, ColumnMetadataService columnMetadataService, GatewayUserRepository gatewayUserRepository, SystemUserFieldValueRepository systemUserFieldValueRepository) {
    this.systemFieldRepository = systemFieldRepository;
    this.systemRepository = systemRepository;
    this.columnMetadataService = columnMetadataService;
    this.gatewayUserRepository = gatewayUserRepository;
    this.systemUserFieldValueRepository = systemUserFieldValueRepository;
  }

  public List<SystemField> getAllSystemFieldBySystemName(String systemName) {
    return systemFieldRepository.findSystemFieldsBySystemName(systemName);
  }

  @Transactional
  public void saveUserFields(List<UserFieldValue> userFieldValues, String system) throws SQLException {
    List<UserFieldValue> userFieldValueToPersist = fetchUserFieldValueToPersist(userFieldValues);
    GatewayUser gatewayUser = populateGatewayUser(userFieldValueToPersist);
    gatewayUserRepository.save(gatewayUser);
    List<SystemUserFieldValue> systemUserFieldValues = fetchSystemUserFieldValues(userFieldValues, system, gatewayUser);
    systemUserFieldValueRepository.saveAll(systemUserFieldValues);
    makePostRequest(system);
  }

  public Object makePostRequest(String system) {
        // Fetch the dynamic URL from the database
        com.nubolerta.tech.entities.System systemDB = systemRepository.findByName(system);

        String dynamicUrl = systemDB.getUrl();

        // Build a Feign client with the dynamic URL
        StringBuilder logBuiler = new StringBuilder();
        logBuiler.append("Execution external service URL :")
        .append(dynamicUrl);
        logger.info("{}",logBuiler);                
        // Make the POST request dynamicFeignClient.postToDynamicUrl(userFieldValues)
        return null;
  }
  private List<SystemUserFieldValue> fetchSystemUserFieldValues(List<UserFieldValue> userFieldValues, String system,
      GatewayUser user) {
    List<SystemField> systemFields = systemFieldRepository.findSystemFieldsBySystemName(system);
    List<SystemUserFieldValue> systemUserFieldValueToPersist = new ArrayList<>();
    for(SystemField systemField : systemFields) {
      for(UserFieldValue userFieldValue : userFieldValues) {
        if(systemField.getName().equalsIgnoreCase(userFieldValue.getFieldName())) {
          SystemField systemFieldFromDB = systemFieldRepository.findByFieldNameAndSystem(systemField.getName(), system);
          SystemUserFieldValue systemUserFieldValue= new SystemUserFieldValue(null , user, systemFieldFromDB , userFieldValue.getFieldValue());
          systemUserFieldValueToPersist.add(systemUserFieldValue);
        }
      }
    }
    return systemUserFieldValueToPersist;
  }

  private List<UserFieldValue> fetchUserFieldValueToPersist(List<UserFieldValue> userFieldValues)  throws SQLException{
    List<UserField> userFields = columnMetadataService
        .getDatabaseColumnDetails(SystemConstants.GATEWAY_USER_TABLE_NAME);
    List<UserFieldValue> userFieldValueToPersist = new ArrayList<>();
    userFields.forEach(userField -> {
      userFieldValueToPersist.addAll(userFieldValues.stream()
          .filter(userFieldValue -> userField.getFieldName().equalsIgnoreCase(userFieldValue.getFieldName()))
          .toList());
    });
    return userFieldValueToPersist;
  }

  private GatewayUser populateGatewayUser(List<UserFieldValue> userFieldValueTOPersist) {
    GatewayUser gatewayUser = new GatewayUser();
    for (java.lang.reflect.Field field : gatewayUser.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(Column.class)) {
        Column column = field.getAnnotation(Column.class);
        for (UserFieldValue userFieldValue : userFieldValueTOPersist) {
          if (userFieldValue.getFieldName().equalsIgnoreCase(column.name())) {
            SystemConstants.setDynamicField(gatewayUser, field.getName(), userFieldValue.getFieldValue());
          }
        }
      }
    }
    return gatewayUser;
  }

}
