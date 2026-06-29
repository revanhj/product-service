--liquibase formatted sql

--changeset revan:006

CREATE TABLE product_service.product_attributes (

    id BIGSERIAL PRIMARY KEY,

    attribute_code VARCHAR(50) NOT NULL UNIQUE,

    attribute_name VARCHAR(100) NOT NULL,

    description VARCHAR(500),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--rollback DROP TABLE product_service.product_attributes;