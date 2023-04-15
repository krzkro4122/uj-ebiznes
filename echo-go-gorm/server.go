package main

import (
	"fmt"
	"github.com/labstack/echo/v4"
	"net/http"
	"os"
	"strconv"
)

func serve(port int) {
	e := echo.New()
	e.GET("/", func(c echo.Context) error {
		return c.String(http.StatusOK, "Hello, World!")
	})
	e.Logger.Fatal(e.Start(fmt.Sprintf(":%d", port)))
}

func main() {
	value, exists := os.LookupEnv("PORT")
	PORT := 9000
	if exists {
		env, err := strconv.Atoi(value)
		if err != nil {
			panic(err)
		} else {
			PORT = env
		}
	}

	fmt.Println("Running the server on: localhost:%d", PORT)
	serve(PORT)
}
