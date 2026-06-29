--liquibase formatted sql

--changeset revan:009

INSERT INTO product_service.categories
(category_code, category_name, description, status)
VALUES
('MOBILE', 'Mobile Phones', 'Mobile Devices', 'ACTIVE'),
('LAPTOP', 'Laptops', 'Laptop Devices', 'ACTIVE'),
('TABLET', 'Tablets', 'Tablet Devices', 'ACTIVE');

INSERT INTO product_service.brands
(brand_code, brand_name, description, status)
VALUES
('APPLE', 'Apple', 'Apple Products', 'ACTIVE'),
('SAMSUNG', 'Samsung', 'Samsung Products', 'ACTIVE'),
('DELL', 'Dell', 'Dell Products', 'ACTIVE'),
('HP', 'HP', 'HP Products', 'ACTIVE');

--rollback

DELETE FROM product_service.brands
WHERE brand_code IN ('APPLE','SAMSUNG','DELL','HP');

DELETE FROM product_service.categories
WHERE category_code IN ('MOBILE','LAPTOP','TABLET');