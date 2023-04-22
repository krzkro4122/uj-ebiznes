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
	// Category
	e.GET("/category/:id", controller.ReadCategory)
	e.GET("/category", controller.ReadAllCategories)
	e.PUT("/category/:id", controller.UpdateCategory)
	e.POST("/category", controller.CreateCategory)
	e.DELETE("/category/:id", controller.DeleteCategory)
	// Cart
	e.GET("/cart/:id", controller.ReadCartMember)
	e.GET("/cart", controller.ReadAllCartMembers)
	e.PUT("/cart/:id", controller.UpdateCartMember)
	e.POST("/cart", controller.CreateCartMember)
	e.DELETE("/cart/:id", controller.DeleteCartMember)
	// Purchase
	e.POST("/cart/buy", controller.BuyCart)
}

func Serve(port string) {
	e := echo.New()
	define_endpoints(e)
	e.Logger.Fatal(e.Start(fmt.Sprintf(":%s", port)))
}
