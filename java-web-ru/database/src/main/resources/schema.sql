-- BEGIN
DROP TABLE IF EXISTS products;

CREATE TABLE products(
id LONG PRIMARY KEY AUTO_INCREMENT,
title TEXT NOT NULL,
price INT NOT NULL
);
-- END