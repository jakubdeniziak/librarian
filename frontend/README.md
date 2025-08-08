# frontend

This is the **Angular-based frontend** of the **Librarian** application.
It provides the user interface for managing books, authors, libraries and more.

## Building and running

To run this application you should follow the steps described in the [root README file](../README.md) as the backend
services are required to access all features.
For development purposes you can use one of the approaches described below.

### Running with Docker (development setup)

```bash
docker build --file Dockerfile.dev --tag librarian-frontend-dev .
docker run --name librarian-frontend-dev --publish 4200:4200 librarian-frontend-dev
```

### Running with Angular CLI (development setup)

```bash
npm install
ng serve
```
