CREATE TABLE users
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100)        NOT NULL,
    last_name  VARCHAR(100)        NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role       VARCHAR(20)         NOT NULL
);

CREATE TABLE products
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(100) NOT NULL,
    description    TEXT,
    price          DOUBLE       NOT NULL,
    stock_quantity INT          NOT NULL
);

CREATE TABLE reviews
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    comment    TEXT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);