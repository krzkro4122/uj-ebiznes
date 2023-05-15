CREATE TABLE Users (
    user_id int PRIMARY KEY,
    username varchar(255) NOT NULL,
    password_hash varchar(255) NOT NULL,
    salt varchar(255) NOT NULL
);
