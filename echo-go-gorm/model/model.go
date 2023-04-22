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
}

type Category struct {
	gorm.Model
	ID   int
	Name string
}

type CartMember struct {
	gorm.Model
	ID         int
	ProductID  int
	Name       string
	Price      int
	Category   string
	CategoryID int
	Quantity   int
}
