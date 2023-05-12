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

type IPayment struct {
	Cvv            string `json:"cvv"`
	ExpirationDate string `json:"expirationDate"`
	CardNumber     string `json:"cardNumber"`
	Amount         int    `json:"amount"`
}

type IPurchase struct {
	Payment  IPayment  `json:"payment"`
	Products []Product `json:"products"`
}
