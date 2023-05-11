# Goofy-ahh e-commerce

This is a front-end for a basic e-commerce application.

## This app has:

- a `shopping pane` component that contains `Card` components. They display items acquired from the server-side.
- The cart endpoint sends the list of desired products to the server along with the payment information (amount).
- A `shopping cart` view, where one can modify the quantities of products in the cart, as well as delete them.
- `Axios` for HTTP communication with the server application.<br/>(CORS is configured on the server).
- Products are automatically re-fetched upon a successful purchase operation.

# Usage

`Frontend`:

- Run with

```
npm run dev
```

Go `Backend` `+` this `Frontend`:

- Build with

```
npm run build
```

- Then:

```
go run ../echo-go-gorm/server.go
```

## Usage snapshots

image.png
image.png
