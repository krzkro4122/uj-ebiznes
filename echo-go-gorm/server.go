package main

import (
	"fmt"
	"os"

	"github.com/krzkro4122/echogogorm/route"
)

func get_port(default_port string) string {
	value, exists := os.LookupEnv("PORT")
	if exists {
		return value
	} else {
		return default_port
	}
}

func main() {
	port := get_port("9000")
	fmt.Printf("Running the server on: http://localhost:%s\n", port)
	route.Serve(port)
}
