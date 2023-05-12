package route

import (
	"fmt"

	"github.com/krzkro4122/echogogorm/controller"
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
)

func define_endpoints(e *echo.Echo) {
	e.Static("/assets", "./public/assets")
	e.GET("/", controller.Index)
	// Product
	e.GET("/product/:id", controller.ReadProduct)
	e.GET("/product", controller.ReadAllProducts)
	e.PUT("/product/:id", controller.UpdateProduct)
	e.POST("/product", controller.CreateProduct)
	e.DELETE("/product/:id", controller.DeleteProduct)
	// Category
	e.GET("/category/:id", controller.ReadCategory)
	e.GET("/category", controller.ReadAllCategories)
	e.PUT("/category/:id", controller.UpdateCategory)
	e.POST("/category", controller.CreateCategory)
	e.DELETE("/category/:id", controller.DeleteCategory)
	// Purchase
	e.POST("/cart/buy", controller.BuyCart)
}

func Serve(port string) {
	e := echo.New()
	e.Use(middleware.CORSWithConfig(middleware.CORSConfig{
		AllowOrigins: []string{"*"},
		AllowHeaders: []string{echo.HeaderOrigin, echo.HeaderContentType, echo.HeaderAccept},
	}))
	define_endpoints(e)
	e.Logger.Fatal(e.Start(fmt.Sprintf("0.0.0.0:%s", port)))
}
