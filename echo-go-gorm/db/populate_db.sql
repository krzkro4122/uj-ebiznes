CREATE TABLE Products (
    id INTEGER PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    name TEXT,
    price INTEGER,
    category TEXT,
    category_id INTEGER,
    quantity INTEGER
);

CREATE TABLE Categories (
    id INTEGER PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    name TEXT
);

CREATE TABLE Cart_Members (
    id INTEGER PRIMARY KEY,
    product_id INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    name TEXT,
    price INTEGER,
    category TEXT,
    category_id INTEGER,
    quantity INTEGER,
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

INSERT INTO Products (created_at, updated_at, deleted_at, name, price, category_id, category, quantity)
VALUES
    ('2023-04-15 10:00:00', '2023-04-15 10:00:00', NULL, 'Product A', 10, 1, 'Category 1', 100),
    ('2023-04-15 10:01:00', '2023-04-15 10:01:00', NULL, 'Product B', 15, 1, 'Category 1', 75),
    ('2023-04-15 10:02:00', '2023-04-15 10:02:00', NULL, 'Product C', 20, 2, 'Category 2', 50),
    ('2023-04-15 10:03:00', '2023-04-15 10:03:00', NULL, 'Product D', 25, 2, 'Category 2', 25),
    ('2023-04-15 10:04:00', '2023-04-15 10:04:00', NULL, 'Product E', 30, 3, 'Category 3', 100),
    ('2023-04-15 10:05:00', '2023-04-15 10:05:00', NULL, 'Product F', 35, 3, 'Category 3', 75),
    ('2023-04-15 10:06:00', '2023-04-15 10:06:00', NULL, 'Product G', 40, 4, 'Category 4', 50),
    ('2023-04-15 10:07:00', '2023-04-15 10:07:00', NULL, 'Product H', 45, 4, 'Category 4', 25),
    ('2023-04-15 10:08:00', '2023-04-15 10:08:00', NULL, 'Product I', 50, 5, 'Category 5', 100),
    ('2023-04-15 10:09:00', '2023-04-15 10:09:00', NULL, 'Product J', 55, 5, 'Category 5', 75);

INSERT INTO Categories (created_at, updated_at, deleted_at, name)
VALUES
    ('2023-04-15 10:01:00', '2023-04-15 10:01:00', NULL, 'Category 1'),
    ('2023-04-15 10:03:00', '2023-04-15 10:03:00', NULL, 'Category 2'),
    ('2023-04-15 10:05:00', '2023-04-15 10:05:00', NULL, 'Category 3'),
    ('2023-04-15 10:07:00', '2023-04-15 10:07:00', NULL, 'Category 4'),
    ('2023-04-15 10:09:00', '2023-04-15 10:09:00', NULL, 'Category 5');
