package com.nubolerta.tech.service;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nubolerta.tech.constants.SystemConstants;
import com.nubolerta.tech.dto.UserField;

@Service
public class ColumnMetadataService {

    private DataSource dataSource;

    @Autowired
    public ColumnMetadataService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<UserField> getDatabaseColumnDetails(String tableName) throws Exception {
        List<UserField> userFields = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            
            while (columns.next()) {
                String columnName = columns.getString(SystemConstants.COLUMN_NAME);
                String columnType = columns.getString(SystemConstants.COLUMN_TYPE);
                columnType = columnType.contains(SystemConstants.DB_TEXT) ? SystemConstants.TEXT : columnType;
                if(!SystemConstants.ID_COLUMN.equalsIgnoreCase(columnName)) {
                    userFields.add(new UserField(columnName, columnType));
                }
            }
        }
        return userFields;
    }
}
