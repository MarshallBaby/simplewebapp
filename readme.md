# Simple Web App

1. Please, create database ***employeedb*** on your PostgreSQL server, then run ***employee.sql*** file.
2. Edit DB credential in ***application.propetries*** file.
3. Run ***mvn package***
4. Execute package by ***java -jar***

UI adress: ***127.0.0.1:8080/***

JSON format of Employee object:
```
{
        "employeeId": 8,
        "firstName": "Ivan",
        "lastName": "Petrov",
        "departmentId": 48,
        "jobTitle": "Tester",
        "gender": "MALE"
    }
```

API:

1. Save: POST *127.0.0.1:8080/api/employee*
2. Edit: PUT *127.0.0.1:8080/api/employee/{id}*
3. Find By Id: GET *127.0.0.1:8080/api/employee/{id}*
4. Find All: GET *127.0.0.1:8080/api/employee*
5. Delete: DELETE *127.0.0.1:8080/api/employee/{id}*
