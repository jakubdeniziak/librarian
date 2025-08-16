# librarian-gateway

This component serves as the API gateway placed between the frontend and the backend services.

## Running

To run this component in production mode you should refer to the project's main [README](../README.md) file.
If you want to run the gateway in development mode it is best to follow the steps described in the same README file.
To run the component as a standalone (without connection to other services) you can use the following commands:

```bash
docker build --file Dockerfile.dev --tag librarian-gateway-dev .
docker run --name librarian-gateway-dev --publish "443:443" librarian-gateway-dev
```
