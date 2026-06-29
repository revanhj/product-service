--liquibase formatted sql

--changeset revan:002

CREATE TABLE product_service.categories (
    id BIGSERIAL PRIMARY KEY,
    category_code VARCHAR(50) NOT NULL UNIQUE,
    category_name VARCHAR(100) NOT NULL,
    description VARCHAR(500),

    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_category_status
        CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

--rollback DROP TABLE product_service.categories;