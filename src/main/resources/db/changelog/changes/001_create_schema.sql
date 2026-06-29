--liquibase formatted sql

--changeset revan:001

CREATE SCHEMA IF NOT EXISTS product_service;

--rollback DROP SCHEMA product_service CASCADE;