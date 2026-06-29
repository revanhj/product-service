--liquibase formatted sql

--changeset revan:004

CREATE TABLE product_service.products (

    id BIGSERIAL PRIMARY KEY,

    sku VARCHAR(100) NOT NULL UNIQUE,

    product_name VARCHAR(255) NOT NULL,

    short_description VARCHAR(500),

    description TEXT,

    price NUMERIC(12,2) NOT NULL,

    quantity INTEGER NOT NULL DEFAULT 0,

    category_id BIGINT NOT NULL,

    brand_id BIGINT NOT NULL,

    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_products_category
        FOREIGN KEY (category_id)
        REFERENCES product_service.categories(id),

    CONSTRAINT fk_products_brand
        FOREIGN KEY (brand_id)
        REFERENCES product_service.brands(id),

    CONSTRAINT chk_product_price
        CHECK (price >= 0),

    CONSTRAINT chk_product_quantity
        CHECK (quantity >= 0),

    CONSTRAINT chk_product_status
        CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

--rollback DROP TABLE product_service.products;