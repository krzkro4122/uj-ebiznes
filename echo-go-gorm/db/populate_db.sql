DROP TABLE Products;
DROP TABLE Categories;

CREATE TABLE Products (
    id INTEGER PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    name TEXT,
    price INTEGER,
    quantity INTEGER,
    stock INTERGER,
    thumbnail TEXT,
    category TEXT,
    category_id INTEGER,
    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
        REFERENCES Categories(id)
);

CREATE TABLE Categories (
    id INTEGER PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    name TEXT
);

INSERT INTO Products (created_at, updated_at, deleted_at, name, price, category_id, category, stock, quantity, thumbnail)
VALUES
    ('2023-04-15 10:00:00', '2023-04-15 10:00:00', NULL, 'Product A', 10, 1, 'Category 1', 100, 0, "https://aws-obg-image-lb-2.tcl.com/content/dam/brandsite/region/maylaysia/products/tvs/p-series/p615/product/EM_55P615_front_global.png"),
    ('2023-04-15 10:01:00', '2023-04-15 10:01:00', NULL, 'Product B', 15, 1, 'Category 1', 75, 0, "https://www.eatthis.com/wp-content/uploads/sites/4/2021/01/soy-milk.jpg?quality=82&strip=1&w=640"),
    ('2023-04-15 10:02:00', '2023-04-15 10:02:00', NULL, 'Product C', 20, 2, 'Category 2', 50, 0, "https://www.wplywaryba.pl/wp-content/uploads/2020/12/Domowe-sushi.jpg"),
    ('2023-04-15 10:03:00', '2023-04-15 10:03:00', NULL, 'Product D', 25, 2, 'Category 2', 25, 0, "https://image.ceneostatic.pl/data/article_picture/75/60/4e26-fddc-4638-b8e2-fe10ed79f3ef_large.jpg"),
    ('2023-04-15 10:04:00', '2023-04-15 10:04:00', NULL, 'Product E', 30, 3, 'Category 3', 100, 0, "https://d.newsweek.com/en/full/1748302/dc.jpg?w=1600&h=1200&q=88&f=6009121f6320b8f52029e73e22d3ce00"),
    ('2023-04-15 10:05:00', '2023-04-15 10:05:00', NULL, 'Product F', 35, 3, 'Category 3', 75, 0, "https://upload.wikimedia.org/wikipedia/commons/e/e1/FullMoon2010.jpg"),
    ('2023-04-15 10:06:00', '2023-04-15 10:06:00', NULL, 'Product G', 40, 4, 'Category 4', 50, 0, "https://gratisography.com/wp-content/uploads/2023/02/gratisography-colorful-kittenfree-stock-photo-800x525.jpg"),
    ('2023-04-15 10:07:00', '2023-04-15 10:07:00', NULL, 'Product H', 45, 4, 'Category 4', 25, 0, "https://usapple.org/wp-content/uploads/2019/10/apple-pink-lady.png"),
    ('2023-04-15 10:08:00', '2023-04-15 10:08:00', NULL, 'Product I', 50, 5, 'Category 5', 100, 0, "https://images.healthshots.com/healthshots/en/uploads/2022/12/16102013/pear-for-skincare-1600x900.jpg"),
    ('2023-04-15 10:09:00', '2023-04-15 10:09:00', NULL, 'Product J', 55, 5, 'Category 5', 75, 0, "https://assets.nintendo.com/image/upload/c_fill,w_1200/q_auto:best/f_auto/dpr_2.0/ncom/en_US/games/switch/c/cat-milk-switch/hero");

INSERT INTO Categories (created_at, updated_at, deleted_at, name)
VALUES
    ('2023-04-15 10:01:00', '2023-04-15 10:01:00', NULL, 'Category 1'),
    ('2023-04-15 10:03:00', '2023-04-15 10:03:00', NULL, 'Category 2'),
    ('2023-04-15 10:05:00', '2023-04-15 10:05:00', NULL, 'Category 3'),
    ('2023-04-15 10:07:00', '2023-04-15 10:07:00', NULL, 'Category 4'),
    ('2023-04-15 10:09:00', '2023-04-15 10:09:00', NULL, 'Category 5');
