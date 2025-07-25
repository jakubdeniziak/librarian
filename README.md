# Librarian

An application that will help manage libraries.


## Running

The entire project can be easily run using `docker compose up --build`.

### Environmental properties

Before running the project you have to create a `.env` file in the project root (where `compose.yaml` is located)
that will contain the following properties:

```properties
# Optional:
BACKEND_VERSION=1.0.0

# Required:
DATABASE_USERNAME=root
DATABASE_PASSWORD=root
DATABASE_NAME=librarian

ADMIN_USERNAME=username
ADMIN_PASSWORD=password
JWT_SECRET=secret
```

You can copy and rename the `.env.example` file that contains all the required properties.
The `JWT_SECRET` must be a base64-encoded string with 256 bits (32 bytes), you can generate it using this command: `openssl rand -base64 32`.


## License

This work is licensed under the MIT license. See the [LICENSE.md](LICENSE.md) file for more information.
