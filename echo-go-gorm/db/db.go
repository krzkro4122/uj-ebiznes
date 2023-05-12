package db

import (
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

// Connect to db with gorm
var Db *gorm.DB = configure_db()

func configure_db() *gorm.DB {
	db, err := gorm.Open(sqlite.Open("./db/db.db"), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}
	db.Table("products")
	db.Table("cart_members")
	return db
}
