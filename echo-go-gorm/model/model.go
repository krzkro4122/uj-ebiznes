package model

import (
	"gorm.io/gorm"
)

// Models
type Product struct {
	gorm.Model
	ID         int
	Name       string
	Price      int
	Category   string
	CategoryID int
	Quantity   int
	Stock      int
	Thumbnail  string
}

type Category struct {
	ID   int
	Name string
}
