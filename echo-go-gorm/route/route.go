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
	e.GET("/product/:id", controller.ReadCartMember)
	e.GET("/product", controller.ReadAllCartMembers)
	e.PUT("/product/:id", controller.UpdateCartMember)
	e.POST("/product", controller.CreateCartMember)
	e.DELETE("/product/:id", controller.DeleteCartMember)
}

func Serve(port string) {
	e := echo.New()
	define_endpoints(e)
	e.Logger.Fatal(e.Start(fmt.Sprintf(":%s", port)))
}
