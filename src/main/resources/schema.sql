CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS accounts (
    id UUID DEFAULT uuid_generate_v4(),
    title VARCHAR(256) NOT NULL,
    balance BIGINT DEFAULT 0,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT PK_account PRIMARY KEY (id)
);