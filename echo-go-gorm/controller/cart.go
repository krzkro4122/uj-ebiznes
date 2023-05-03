package controller

import (
	"fmt"
	"net/http"
	"strconv"

	"github.com/krzkro4122/echogogorm/model"
	"github.com/labstack/echo/v4"
)

type IPurchase struct {
	payment  int `json:"payment"`
	products []model.Product
}

func BuyCart(c echo.Context) error {
	var body IPurchase

	if err := c.Bind(&body); err != nil {
		return err
	}

	payment := body.payment
	products := body.products

	for _, desiredProduct := range products {
		product, err := get_product(strconv.Itoa(desiredProduct.ID))
		if desiredProduct.Quantity > product.Quantity || err != nil {
			return c.JSON(
				http.StatusBadRequest,
				map[string]string{
					"error": fmt.Sprintf(
						"Not enough of %s in stock: %d, requested: %d (lacking %d)",
						product.Name,
						product.Quantity,
						desiredProduct.Quantity,
						product.Quantity-desiredProduct.Quantity,
					),
				},
			)
		}
	}

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

	return c.JSON(http.StatusOK, products)
}
