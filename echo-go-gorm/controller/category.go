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

func get_category(id string) (model.Category, error) {
	var category model.Category
	if err := db.Db.First(&category, id).Error; err != nil {
		// Return a 404 response if the product does not exist
		if errors.Is(err, gorm.ErrRecordNotFound) {
			return category, err
		}
	}
	return category, nil
}

func filter(products []model.Product, cond func(product model.Product) bool) []model.Product {

	result := []model.Product{}

	for _, product := range products {
		if cond(product) {
			result = append(result, product)
		}
	}
	return result
}

// Category
func ReadCategory(c echo.Context) error {
	id := c.Param("id")

	category, err := get_category(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(
			http.StatusNotFound,
			map[string]string{
				"error": "Category with ID: " + id + " not found",
			},
		)
	}

	all_products := get_all_products()
	all_products = filter(all_products, func(product model.Product) bool {
		return product.Category == category.Name
	})

	return c.JSON(http.StatusOK, all_products)
}

func ReadAllCategories(c echo.Context) error {
	var categories []model.Category
	db.Db.Find(&categories)
	return c.JSON(http.StatusOK, categories)
}

func UpdateCategory(c echo.Context) error {
	id := c.Param("id")
	var body model.Category

	// Bind the JSON data from the request body to your struct
	if err := c.Bind(&body); err != nil {
		return err
	}

	category, err := get_category(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(
			http.StatusNotFound,
			map[string]string{
				"error": "Category with ID: " + id + " not found",
			},
		)
	}

	db.Db.Model(&category).Update("Name", body.Name)
	return c.JSON(http.StatusOK, category)
}

func CreateCategory(c echo.Context) error {
	var body model.Category

	// Bind the JSON data from the request body to your struct
	if err := c.Bind(&body); err != nil {
		return err
	}

	_, err := get_category(strconv.Itoa(body.ID))
	if !errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(
			http.StatusNotFound,
			map[string]string{
				"error": "Category with ID: " + strconv.Itoa(body.ID) + " already exists",
			},
		)
	}

	var category = model.Category{
		ID:   body.ID,
		Name: body.Name,
	}
	db.Db.Create(&category)
	return c.JSON(http.StatusOK, category)
}

func DeleteCategory(c echo.Context) error {
	id := c.Param("id")

	category, err := get_category(id)
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return c.JSON(
			http.StatusNotFound,
			map[string]string{
				"error": "Category with ID: " + id + " not found",
			},
		)
	}
	db.Db.Delete(&category)
	return c.JSON(http.StatusOK, category)
}
