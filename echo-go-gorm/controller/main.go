package controller

import (
	"github.com/labstack/echo/v4"
)

func Index(c echo.Context) error {
	return c.File("./public/index.html")
}
