# Simple Web App

### UI Adress
0.0.0.0:8080
### REST API Swagger Docs
0.0.0.0:8080/swagger-ui.html

### (26.03.22) Change log:
#### Fixed:
##### *Optional* replaced with *Employee* object in controllers return type.
ResourceNotFoundException now thrown explicitly from service layer by checking Optional object returned from repository layer.

##### *findEmployee* contoller method fix
Removed garbage-code from *findEmployee* controller. LIKE-based custom repository method replaced with CONTAINS-based method.

##### *updateEmployee* controller method fix
Now the updateEmployee method may not receive the full Employee object as the request body, but only those fields that will be edited as a result.

##### application.properties is replaced with application.yml
IMHO it feels much cleaner :)

##### pom.xml cleanup
Spring-JDBC dependency is removed.

#### New:

##### Age 18+ validation
Added @Min(18) annotation on Employee *age* property.

##### JMS
Added *send* method (invoked in EmployeeService.findEmployee method) and a listener. Works fine :)

##### Logger
Added one custom log4j logger (by.marshallbaby.jms) and appender to log incoming JMS messages. Slf4j is used also.

##### Swagger
Swagger is applied. Auto-generation is disabled to keep code clean. OpenAPI yaml code is located in *static/openapi.yaml*

##### Liquibase
Liquibase is applied.

##### Docker
The project has been dockerised.
