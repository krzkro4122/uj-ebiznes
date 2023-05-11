# E-commerce CRUD backend

Written in `Go` along with the `Echo` HTTP framework and the `Gorm` ORM.

## This app has:

- a `Product`, `Category` and `Cart` controller
- backed by a `Product` and `Category` gorm models (connected with the CategoryID reference ID)
- The shopping cart endpoint IS NOT persistent. Persistent storage of shopping cart elements is delegated to the Client application (f.e. local storage or browser cache) since there are no user accounts/models in this app.

Can be tested with the `InsomniaCollection.json` HTTP collection using [insomnia](https://insomnia.rest/).

# Run with

```
go run server.go
```

TIP: You can also use it with the `frontend` from `../shop-front` by going to `http://localhost:9000`
