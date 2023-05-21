CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username varchar(255) NOT NULL UNIQUE,
    password_hash varchar(255) NOT NULL,
    salt varchar(255) NOT NULL
);

CREATE TABLE Tokens (
    id SERIAL PRIMARY KEY,
    owner_id int,
    valid_until TIMESTAMP NOT NULL,
    token varchar(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY(owner_id)
            REFERENCES Users(id)
);