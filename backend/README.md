# librarian-backend

This service is responsible for communicating with the database and exposing an API.

## Running

To run this component in production mode you should refer to the project's main [README](../README.md) file.
If you want to run the backend in development mode it is best to follow the steps described in the same README file.
To run the component as a standalone (without connection to other services) you can use the following commands:

```bash
docker build --file Dockerfile.dev --tag librarian-backend-dev .
docker run --name librarian-backend-dev --publish "8081:8081" librarian-backend-dev
```

## API endpoints

API documentation is available in OpenAPI Specification. It can be accessed by using [this endpoint](http://localhost:8080/api/docs).
It is also available for download in [json](http://localhost:8080/api/docs/download) and [yaml](http://localhost:8080/api/docs/download.yaml).

The API is available in two formats:
- `/v1`: standard
- `/v2`: HATEOAS based (implemented only for some endpoints)

## Code coverage

To generate a JaCoCo coverage report and check whether the coverage is above 80% you can run `./mvnw verify`, it will fail if the coverage condition is not met.

## Miscellaneous

This service supports Actuator statistics that can be accessed [here](http://localhost:8080/api/actuator).
