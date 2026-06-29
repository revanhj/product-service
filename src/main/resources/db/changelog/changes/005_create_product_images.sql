--liquibase formatted sql

--changeset revan:005

CREATE TABLE product_service.product_images (

    id BIGSERIAL PRIMARY KEY,

    product_id BIGINT NOT NULL,

    image_url VARCHAR(1000) NOT NULL,

    is_primary BOOLEAN DEFAULT FALSE,

    display_order INTEGER DEFAULT 1,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_images_product
        FOREIGN KEY (product_id)
        REFERENCES product_service.products(id)
        ON DELETE CASCADE
);

--rollback DROP TABLE product_service.product_images;