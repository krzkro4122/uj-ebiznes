package controller

import (
	"fmt"
	"net/http"
	"strconv"

	"github.com/krzkro4122/echogogorm/db"
	"github.com/krzkro4122/echogogorm/model"
	"github.com/labstack/echo/v4"
)

type IPurchase struct {
	Payment  int             `json:"payment"`
	Products []model.Product `json:"products"`
}

func BuyCart(c echo.Context) error {
	var body IPurchase

	if err := c.Bind(&body); err != nil {
		return err
	}

	payment := body.Payment
	products := body.Products

	// Check quantities
	for _, desiredProduct := range products {
		product, err := get_product(strconv.Itoa(desiredProduct.ID))
		if desiredProduct.Quantity > product.Stock || err != nil {
			return c.JSON(
				http.StatusBadRequest,
				map[string]string{
					"error": fmt.Sprintf(
						"Not enough of %s in stock: %d, requested: %d (lacking %d)",
						product.Name,
						product.Stock,
						desiredProduct.Quantity,
						product.Stock-desiredProduct.Quantity,
					),
				},
			)
		}
	}

	// Money check
	totalPrice := 0
	for _, product := range products {
		totalPrice += product.Price * product.Quantity
	}

	if payment < totalPrice {
		return c.JSON(
			http.StatusBadRequest,
			map[string]string{
				"error": fmt.Sprintf(
					"Not enough funds to buy, got: $%d, total cost: $%d (lacking $%d)",
					totalPrice,
					payment,
					totalPrice-payment,
				),
			},
		)
	}

	// Deplete the actual product quantities
	for _, desiredProduct := range products {
		product, err := get_product(strconv.Itoa(desiredProduct.ID))
		if err != nil {
			return c.JSON(
				http.StatusBadRequest,
				map[string]string{"error": "Sth weird happened lol"},
			)
		}
		product.Stock = product.Stock - desiredProduct.Quantity
		db.Db.Model(&product).Update("Stock", product.Stock)
	}

	return c.JSON(http.StatusOK, products)
}
