--liquibase formatted sql

--changeset revan:008

CREATE INDEX idx_categories_code
ON product_service.categories(category_code);

CREATE INDEX idx_brands_code
ON product_service.brands(brand_code);

CREATE INDEX idx_products_sku
ON product_service.products(sku);

CREATE INDEX idx_products_category
ON product_service.products(category_id);

CREATE INDEX idx_products_brand
ON product_service.products(brand_id);

CREATE INDEX idx_product_images_product
ON product_service.product_images(product_id);

CREATE INDEX idx_pav_product
ON product_service.product_attribute_values(product_id);

CREATE INDEX idx_pav_attribute
ON product_service.product_attribute_values(attribute_id);

--rollback

DROP INDEX IF EXISTS product_service.idx_categories_code;
DROP INDEX IF EXISTS product_service.idx_brands_code;
DROP INDEX IF EXISTS product_service.idx_products_sku;
DROP INDEX IF EXISTS product_service.idx_products_category;
DROP INDEX IF EXISTS product_service.idx_products_brand;
DROP INDEX IF EXISTS product_service.idx_product_images_product;
DROP INDEX IF EXISTS product_service.idx_pav_product;
DROP INDEX IF EXISTS product_service.idx_pav_attribute;