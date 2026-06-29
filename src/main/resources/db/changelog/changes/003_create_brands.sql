--liquibase formatted sql

--changeset revan:003

CREATE TABLE product_service.brands (
    id BIGSERIAL PRIMARY KEY,
    brand_code VARCHAR(50) NOT NULL UNIQUE,
    brand_name VARCHAR(100) NOT NULL,
    description VARCHAR(500),

    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_brand_status
        CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

--rollback DROP TABLE product_service.brands;