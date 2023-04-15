package model

import (
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

// Connect to db with gorm
var Db *gorm.DB = configure_db()

// Models
type Product struct {
	gorm.Model
	ID       int
	Name     string
	Price    int
	Category string
	Quantity int
}

func configure_db() *gorm.DB {
	db, err := gorm.Open(sqlite.Open("./db/db.db"), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}
	db.Table("products")
	return db
}
