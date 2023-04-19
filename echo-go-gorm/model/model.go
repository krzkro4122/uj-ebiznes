package model

import (
	"gorm.io/gorm"
)

// Models
type Product struct {
	gorm.Model
	ID       int
	Name     string
	Price    int
	Category string
	Quantity int
}

type CartMember struct {
	gorm.Model
	ID        int
	ProductID int
	Name      string
	Price     int
	Category  string
	Quantity  int
}
