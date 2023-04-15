package main

import (
	"errors"
	"fmt"
	"net/http"
	"os"

	"github.com/labstack/echo/v4"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

// Connect to db with gorm
var db *gorm.DB = configure_db()

// Models
type Product struct {
	gorm.Model
	id       int
	Name     string
	Price    int
	Category string
	Quantity int
}

// Helpers
func get_product(id string) (Product, error) {
	var product Product
	if err := db.First(&product, id).Error; err != nil {
		// Return a 404 response if the product does not exist
		if errors.Is(err, gorm.ErrRecordNotFound) {
			return product, err
		}
	}
	return product, nil
}

func get_port(default_port string) string {
	value, exists := os.LookupEnv("PORT")
	if exists {
		return value
	} else {
		return default_port
	}
}

// CRUD
func readProduct(c echo.Context) error {
	id := c.Param("id")
	product, err := get_product(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Product not found"})
	}
	return c.JSON(http.StatusOK, product)
}

func readAllProducts(c echo.Context) error {
	var products []Product
	db.Find(&products)
	return c.JSON(http.StatusOK, products)
}

func updateProduct(c echo.Context) error {
	id := c.Param("id")
	var product Product
	product, err := get_product(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Product not found"})
	}
	db.Model(&product).Update("Name", "LOOOOOL")
	return c.JSON(http.StatusOK, product)
}

func createProduct(c echo.Context) error {
	// Create a new instance of your struct to hold the JSON data
	var body Product

	// Bind the JSON data from the request body to your struct
	if err := c.Bind(&body); err != nil {
		return err
	}
	var product = Product{
		id:       body.id,
		Name:     body.Name,
		Price:    body.Price,
		Category: body.Category,
		Quantity: body.Quantity,
	}
	db.Create(&product)
	return c.JSON(http.StatusOK, product)
}

func deleteProduct(c echo.Context) error {
	id := c.Param("id")
	var product Product
	product, err := get_product(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Product not found"})
	}
	db.Delete(&product)
	return c.JSON(http.StatusOK, product)
}

func define_endpoints(e *echo.Echo) {
	// Product
	e.GET("/product/:id", readProduct)
	e.GET("/product", readAllProducts)
	e.PUT("/product/:id", updateProduct)
	e.POST("/product", createProduct)
	e.DELETE("/product/:id", deleteProduct)
	// Cart
	// e.GET("/product/:id", readProduct)
	// e.GET("/product", readAllProducts)
	// e.PUT("/product/:id", updateProduct)
	// e.POST("/product", createProduct)
	// e.DELETE("/product/:id", deleteProduct)
}

func serve(port string) {
	e := echo.New()
	define_endpoints(e)
	e.Logger.Fatal(e.Start(fmt.Sprintf(":%s", port)))
}

func configure_db() *gorm.DB {
	db, err := gorm.Open(sqlite.Open("db.db"), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}
	db.Table("products")
	return db
}

func main() {
	port := get_port("9000")
	fmt.Printf("Running the server on: localhost:%s\n", port)
	serve(port)
}
