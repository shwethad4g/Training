CREATE DATABASE ecommerce_dress_store;

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    address TEXT
);


CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT,
    price DECIMAL(10,2),
    stock_quantity INT,
    category VARCHAR(50),
    size VARCHAR(10)
);


CREATE TABLE cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);


CREATE TABLE cart_items (
    cart_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id BIGINT,
    product_id BIGINT,
    quantity INT,
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);


CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2),
    status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE order_items (
    order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,  -- Match with orders.order_id
    product_id BIGINT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);




SHOW CREATE TABLE cart;

ALTER TABLE cart 
MODIFY COLUMN cart_id BIGINT NOT NULL AUTO_INCREMENT;


CREATE TABLE cart_items (
    cart_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id BIGINT,
    product_id BIGINT,
    quantity INT,
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);


INSERT INTO products (name, description, price, stock_quantity, category, size)
VALUES 
('Summer Floral Dress', 'Floral cotton dress', 1299.00, 20, 'Casual', 'M'),
('Black Evening Gown', 'Elegant long gown', 3499.00, 10, 'Partywear', 'L'),
('Formal Office Wear', 'Navy blue formal dress', 1999.00, 15, 'Formal', 'S');


ALTER TABLE order_items DROP FOREIGN KEY order_items_ibfk_1;

-- Step 2: Modify both columns to the same type (BIGINT)
ALTER TABLE orders MODIFY COLUMN order_id BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE order_items MODIFY COLUMN order_id BIGINT;

-- Step 3: Re-create the foreign key with consistent types
ALTER TABLE order_items ADD CONSTRAINT order_items_ibfk_1
FOREIGN KEY (order_id) REFERENCES orders(order_id);


ALTER TABLE cart_items ADD customer_id BIGINT; 


SELECT * FROM cart_items WHERE customer_id = 3;



DESCRIBE customers;

DESCRIBE cart_items;
ALTER TABLE cart_items DROP COLUMN customer_id;

ALTER TABLE cart_items CHANGE customer_customer_id customer_id INT;

ALTER TABLE cart_items
ADD CONSTRAINT fk_cart_items_customer
FOREIGN KEY (customer_id)
REFERENCES customers(customer_id)
ON DELETE CASCADE;


SELECT * FROM cart_items WHERE customer_id = 4;

DROP TABLE IF EXISTS cart_items;

DROP TABLE IF EXISTS cart_items;

CREATE TABLE cart_items (
    cart_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id BIGINT,
    product_id BIGINT,
    quantity INT,
    customer_id INT,

    CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(product_id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
















