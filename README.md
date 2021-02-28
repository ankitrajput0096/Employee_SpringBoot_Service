# Multi-Module Spring Boot Application

## Application Overview
Employee service is a spring boot rest application which would provide the CRUD operations for `Employee` resource.

There are three modules in this application
- employeeservApi - This module contains the interface.
	- `v1/schema/employee.json` defines the employee resource.
	- `jsonschema2pojo-maven-plugin` is being used to create `Employee POJO` from json file.
	- `EmployeeResource.java` is the interface for CRUD operations on `Employee` resource.
		- GET `/v1/bfs/employees/{id}` endpoint is defined to fetch the resource.
- employeeservImplementation - This module contains the implementation for the rest endpoints.
	- `EmployeeResourceImpl.java` implements the `EmployeeResource` interface.
- employeeservFunctionalTests - This module would have the functional tests.

## Important Points
- Assumption - I have assumed that each employee will have unique `employee-id`.
- Use command `mvn clean install && mvn spring-boot:run -pl employeeservImplementation` to build and run the project directly from base folder.
- Also, use command `java -jar employeeservImplementation/target/employeeservImplementation-0.0.1-SNAPSHOT-exe.jar` to run the application from packaged application jar file from base folder after running the above command.
- Curl commands for the following operations:
	- `curl --request GET 'http://localhost:8080/v1/bfs/employees/1'` - to fetch the employee resource from DB
	- `curl --request POST 'http://localhost:8080/v1/bfs/employees' --header 'Content-Type: application/json' --data-raw '{
	  "id": 1,
	  "first_name": "Ankit",
	  "last_name": "Developer",
	  "date_of_birth": "31/12/1998",
	  "address": {
	  "line1": "House No: 24, 3nd Cross, Durga Nagar",
	  "line2": "Vidyaranyapura, Bangalore",
	  "city": "Bangalore",
	  "state": "Karnataka",
	  "country": "India",
	  "zip_code": 560092
	  }
	  }'` - to create the employee resource in DB
- Also, sharing the postman collection for above curls in this repository.
	- Import Postman collection from `Employee_Service_Curls.postman_collection.json` file
	
## How to run the application
- Please have Maven version `3.3.3` & Java 8 on your system.
- Use command `mvn clean install` to build the project.
- Use command `mvn spring-boot:run` from `employeeservImplementation` folder to run the project.
- Use postman or curl to access `http://localhost:8080/v1/bfs/employees/1` GET endpoint. It will return an Employee resource.

## Assignment
We would like you to enhance the existing project and see you complete the following requirements:

- [x] `employee.json` has only `name`, and `id` elements. Please add `date of birth` and `address` elements to the `Employee` resource. Address will have `line1`, `line2`, `city`, `state`, `country` and `zip_code` elements. `line2` is an optional element.
- [x] Add one more operation in `EmployeeResource` to create an employee. `EmployeeResource` will have two operations, one to create, and another to retrieve the employee resource.
- [x] Implement create and retrieve operations in `EmployeeResourceImpl.java`.
- [x] Resource created using create endpoint should be retrieved using retrieve/get endpoint.
- [x] Please add the unit tests to validate your implementation.
- [x] Please use h2 in-memory database or any other in-memory database to persist the `Employee` resource. Dependency for h2 in-memory database is already added to the parent pom.
- [x] Please make sure the validations are done for the requests.
- [x] Response codes are as per rest guidelines.
- [x] Error handling in case of failures.
- [x] Idempotency logic is implemented to avoid duplicate resource