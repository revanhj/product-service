--liquibase formatted sql

--changeset revan:007

CREATE TABLE product_service.product_attribute_values (

    id BIGSERIAL PRIMARY KEY,

    product_id BIGINT NOT NULL,

    attribute_id BIGINT NOT NULL,

    attribute_value VARCHAR(255) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_pav_product
        FOREIGN KEY (product_id)
        REFERENCES product_service.products(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_pav_attribute
        FOREIGN KEY (attribute_id)
        REFERENCES product_service.product_attributes(id)
        ON DELETE CASCADE
);

--rollback DROP TABLE product_service.product_attribute_values;