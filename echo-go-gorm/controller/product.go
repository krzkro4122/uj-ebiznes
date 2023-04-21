package controller

import (
	"errors"
	"net/http"
	"strconv"

	"github.com/krzkro4122/echogogorm/db"
	"github.com/krzkro4122/echogogorm/model"
	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
)

func get_product(id string) (model.Product, error) {
	var product model.Product
	if err := db.Db.First(&product, id).Error; err != nil {
		// Return a 404 response if the product does not exist
		if errors.Is(err, gorm.ErrRecordNotFound) {
			return product, err
		}
	}
	return product, nil
}

// PRODUCTS
func ReadProduct(c echo.Context) error {
	id := c.Param("id")
	product, err := get_product(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Product not found"})
	}
	return c.JSON(http.StatusOK, product)
}

func ReadAllProducts(c echo.Context) error {
	var products []model.Product
	db.Db.Find(&products)
	return c.JSON(http.StatusOK, products)
}

func UpdateProduct(c echo.Context) error {
	id := c.Param("id")
	var body model.Product
	var product model.Product

	// Bind the JSON data from the request body to your struct
	if err := c.Bind(&body); err != nil {
		return err
	}

	product, err := get_product(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(
			http.StatusNotFound,
			map[string]string{
				"error": "Product with ID: " + id + " not in stock",
			},
		)
	}

	stockQuantity := product.Quantity
	if body.Quantity > stockQuantity {
		return c.JSON(
			http.StatusNotFound,
			map[string]string{
				"error": "Not enough of product with ID: " + id +
					" in stock. Requested: " + strconv.Itoa(body.Quantity) +
					", available: " + strconv.Itoa(stockQuantity),
			},
		)
	}

	db.Db.Model(&product).Update("Quantity", body.Quantity)
	return c.JSON(http.StatusOK, product)
}

func CreateProduct(c echo.Context) error {
	// Create a new instance of your struct to hold the JSON data
	var body model.Product

	// Bind the JSON data from the request body to your struct
	if err := c.Bind(&body); err != nil {
		return err
	}
	var product = model.Product{
		ID:       body.ID,
		Name:     body.Name,
		Price:    body.Price,
		Category: body.Category,
		Quantity: body.Quantity,
	}
	db.Db.Create(&product)
	return c.JSON(http.StatusOK, product)
}

func DeleteProduct(c echo.Context) error {
	id := c.Param("id")
	var product model.Product
	product, err := get_product(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Product not found"})
	}
	db.Db.Delete(&product)
	return c.JSON(http.StatusOK, product)
}
