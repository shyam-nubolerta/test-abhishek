APIs created#

1. GET http://localhost:8080/api-gateway/api/systems/{system}/form-fields
   API to get all form fields required to be filled in by user
   For example# http://localhost:8080/api-gateway/api/systems/system1/form-fields

2. POST http://localhost:8080/api-gateway/api/systems/{system}/users
   API to save the fields for a particular system for a user.

Add Request Header # Content-Type : application/json

For example# http://localhost:8080/api-gateway/api/systems/system1/users
[
{
"fieldName" : "FIRST_NAME",
"fieldValue" : "AAA"
},
{
"fieldName" : "LAST_NAME",
"fieldValue" : "AA@"
},
{
"fieldName" : "Birthdate",
"fieldValue" : "17/03/83"
},

{
"fieldName" : "Birthplace",
"fieldValue" : "Delhi"
},
{
"fieldName" : "sex",
"fieldValue" : "male"
}
]

3. GET http://localhost:8080/api-gateway/api/users
   API to check data in GW_USER table

DB used#
Used in memory db H2. http://localhost:8080/api-gateway//h2-console
DDL # #src/main/resources/schema.sql
DML # #src/main/resources/data.sql
