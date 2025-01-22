# Librarian backend service

This service is responsible for communicating with the database and exposing an API.


## Documentation

API documentation is available in OpenAPI Specification. It can be accessed here: [UI version](http://localhost:8080/api/docs).
It is available for download in two formats: [json](http://localhost:8080/api/docs/download) and [yaml](http://localhost:8080/api/docs/download.yaml).


## Code coverage

To generate a JaCoCo coverage report and check whether the coverage is above 80% you can run `./mvnw verify`, it will fail if the coverage condition is not met.


## Miscellaneous

This service supports Actuator statistics that can be accessed [here](http://localhost:8080/api/actuator).
