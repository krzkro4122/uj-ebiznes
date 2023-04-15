package route

import (
	"fmt"

	"github.com/krzkro4122/echogogorm/controller"
	"github.com/labstack/echo/v4"
)

func define_endpoints(e *echo.Echo) {
	// Product
	e.GET("/product/:id", controller.ReadProduct)
	e.GET("/product", controller.ReadAllProducts)
	e.PUT("/product/:id", controller.UpdateProduct)
	e.POST("/product", controller.CreateProduct)
	e.DELETE("/product/:id", controller.DeleteProduct)
	// Cart
	// e.GET("/product/:id", readProduct)
	// e.GET("/product", readAllProducts)
	// e.PUT("/product/:id", updateProduct)
	// e.POST("/product", createProduct)
	// e.DELETE("/product/:id", deleteProduct)
}

func Serve(port string) {
	e := echo.New()
	define_endpoints(e)
	e.Logger.Fatal(e.Start(fmt.Sprintf(":%s", port)))
}
