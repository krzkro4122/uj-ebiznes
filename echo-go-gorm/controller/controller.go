package controller

import (
	"errors"
	"fmt"
	"net/http"
	"strconv"

	"github.com/krzkro4122/echogogorm/db"
	"github.com/krzkro4122/echogogorm/model"
	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
)

// Helpers
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

func get_cart_member(id string) (model.CartMember, error) {
	var cartMember model.CartMember
	if err := db.Db.First(&cartMember, id).Error; err != nil {
		// Return a 404 response if the product does not exist
		if errors.Is(err, gorm.ErrRecordNotFound) {
			return cartMember, err
		}
	}
	return cartMember, nil
}

type IPurchase struct {
	Money int `json:"money"`
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

// CART
func ReadCartMember(c echo.Context) error {
	id := c.Param("id")
	product, err := get_cart_member(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Product not found"})
	}
	return c.JSON(http.StatusOK, product)
}

func ReadAllCartMembers(c echo.Context) error {
	var cart []model.CartMember
	db.Db.Find(&cart)
	return c.JSON(http.StatusOK, cart)
}

func UpdateCartMember(c echo.Context) error {
	id := c.Param("id")
	var body model.CartMember
	var cartMember model.CartMember

	// Bind the JSON data from the request body to your struct
	if err := c.Bind(&body); err != nil {
		return err
	}

	cartMember, err := get_cart_member(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(
			http.StatusNotFound,
			map[string]string{
				"error": "Cart member with ID: " + id + " not in cart",
			},
		)
	}

	product, err := get_product(strconv.Itoa(cartMember.ProductID))
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
				"error": "Not enough of product with ID: " + strconv.Itoa(cartMember.ProductID) +
					" in stock. Requested: " + strconv.Itoa(body.Quantity) +
					", available: " + strconv.Itoa(stockQuantity),
			},
		)
	}

	db.Db.Model(&cartMember).Update("Quantity", body.Quantity)
	return c.JSON(http.StatusOK, cartMember)
}

func CreateCartMember(c echo.Context) error {
	var body model.CartMember

	// Bind the JSON data from the request body to your struct
	if err := c.Bind(&body); err != nil {
		return err
	}

	var product model.Product
	product, err := get_product(strconv.Itoa(body.ProductID))

	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(
			http.StatusNotFound,
			map[string]string{
				"error": "Product with ID " + strconv.Itoa(body.ProductID) + " not in stock",
			},
		)
	}

	var cartMember = model.CartMember{
		ID:        body.ID,
		ProductID: body.ProductID,
		Name:      product.Name,
		Price:     product.Price,
		Category:  product.Category,
		Quantity:  body.Quantity,
	}
	db.Db.Create(&cartMember)
	return c.JSON(http.StatusOK, cartMember)
}

func DeleteCartMember(c echo.Context) error {
	id := c.Param("id")
	var cartMember model.CartMember
	cartMember, err := get_cart_member(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Cart member with ID " + id + " not in cart"})
	}
	db.Db.Delete(&cartMember)
	return c.JSON(http.StatusOK, cartMember)
}

func BuyCart(c echo.Context) error {
	var body IPurchase

	if err := c.Bind(&body); err != nil {
		return err
	}

	var cart []model.CartMember
	db.Db.Find(&cart)

	for _, cartMember := range cart {
		product, err := get_product(strconv.Itoa(cartMember.ProductID))
		if product.Quantity < cartMember.Quantity || err != nil {
			return c.JSON(
				http.StatusBadRequest,
				map[string]string{
					"error": fmt.Sprintf(
						"Not enough product in stock, got: %d, need: %d (lacking %d)",
						product.Quantity,
						cartMember.Quantity,
						cartMember.Quantity-product.Quantity,
					),
				},
			)
		}
	}

	totalPrice := 0
	for _, cartMember := range cart {
		totalPrice += cartMember.Price * cartMember.Quantity
	}

	if body.Money < totalPrice {
		return c.JSON(
			http.StatusBadRequest,
			map[string]string{
				"error": fmt.Sprintf(
					"Not enough money to buy, got: $%d, total cost: $%d (lacking $%d)",
					totalPrice,
					body.Money,
					totalPrice-body.Money,
				),
			},
		)
	}

	var cartTemp = cart
	db.Db.Delete(&cart)
	return c.JSON(http.StatusOK, cartTemp)
}
