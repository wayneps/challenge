Application Guide


The web application has two assigned users - admin1 with a role giving global access, and user1 with a role giving access only to REST resources.Both admin1 and user1 have password set ti 'password1'.

The current database is an H2 embedded database and can be easily swapped out for another database such as MySQL by replacing the maven dependency and application propeties. The H2 console can be accessed with user/pass 'sa/password' at 'http://localhost:8080/h2'.

The Database is populated with seed data at application startup.

Resource URLs:
GET http://localhost:8080/apiv1/enrollees
GET http://localhost:8080/apiv1/enrollees/{enrolleeId}
POST http://localhost:8080/apiv1/enrollees
DELETE http://localhost:8080/apiv1/enrollees/{enrolleeId}

GET http://localhost:8080/apiv1/enrollees/{enrolleeId}/dependents
GET http://localhost:8080/apiv1/dependents
GET http://localhost:8080/apiv1/dependents/{dependentId}
POST http://localhost:8080/apiv1/enrollees/{enrolleeId}/dependent
DELETE http://localhost:8080/apiv1/dependents/{dependentId}


Console command:
java -jar enrollee-service-0.0.1-SNAPSHOT.jar




