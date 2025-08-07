# frontend

This is the **Angular-based frontend** of the **Librarian** application.
It provides the user interface for managing books, authors, libraries and more.

## Building and running

To get the full experience while using this application you should follow the steps described in
the [root README file](../README.md) as the backend services are required to access all features.
If you just want to see the home page, you can follow one of the approaches described below.

### Running with Docker (production setup)

This application can be run using docker by executing the following commands:

```bash
docker build -t librarian-frontend .
docker run --name librarian-frontend -p 4200:4200 librarian-frontend
```

### Running with Angular CLI (development setup)

```bash
npm install
ng serve
```
