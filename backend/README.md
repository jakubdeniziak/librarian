# Librarian backend service

This service is responsible for communicating with the database and exposing an API.


## API endpoints

API documentation is available in OpenAPI Specification. It can be accessed by using [this endpoint](http://localhost:8080/api/docs).
It is also available for download in two formats: [json](http://localhost:8080/api/docs/download) and [yaml](http://localhost:8080/api/docs/download.yaml).

The API is available in two formats:
- `/v1`: standard
- `/v2`: HATEOAS based (implemented only for some endpoints)
