CREATE TABLE Products (
    id INTEGER PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    name TEXT,
    price INTEGER,
    category TEXT,
    quantity INTEGER
);

INSERT INTO Products (created_at, updated_at, deleted_at, name, price, category, quantity)
VALUES
    ('2023-04-15 10:00:00', '2023-04-15 10:00:00', NULL, 'Product A', 10, 'Category 1', 100),
    ('2023-04-15 10:01:00', '2023-04-15 10:01:00', NULL, 'Product B', 15, 'Category 1', 75),
    ('2023-04-15 10:02:00', '2023-04-15 10:02:00', NULL, 'Product C', 20, 'Category 2', 50),
    ('2023-04-15 10:03:00', '2023-04-15 10:03:00', NULL, 'Product D', 25, 'Category 2', 25),
    ('2023-04-15 10:04:00', '2023-04-15 10:04:00', NULL, 'Product E', 30, 'Category 3', 100),
    ('2023-04-15 10:05:00', '2023-04-15 10:05:00', NULL, 'Product F', 35, 'Category 3', 75),
    ('2023-04-15 10:06:00', '2023-04-15 10:06:00', NULL, 'Product G', 40, 'Category 4', 50),
    ('2023-04-15 10:07:00', '2023-04-15 10:07:00', NULL, 'Product H', 45, 'Category 4', 25),
    ('2023-04-15 10:08:00', '2023-04-15 10:08:00', NULL, 'Product I', 50, 'Category 5', 100),
    ('2023-04-15 10:09:00', '2023-04-15 10:09:00', NULL, 'Product J', 55, 'Category 5', 75);
