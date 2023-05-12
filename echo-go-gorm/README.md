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

# E2E tests with cypress
- To run cypress e2e tests:
```
npm run e2e
```
# E2E Test results:

![image](https://github.com/krzkro4122/uj-ebiznes/assets/75375838/c0368305-e7ff-4eaf-a63e-7d0834a615ba)

# Github Actions
This dir is also linted on every push/PR (hooks) with `gofmt` along with github actions.
