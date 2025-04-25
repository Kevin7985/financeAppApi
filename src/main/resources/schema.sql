CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS accounts (
    id UUID DEFAULT uuid_generate_v4(),
    title VARCHAR(256) NOT NULL,
    balance BIGINT DEFAULT 0,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT PK_account PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS operations (
    id UUID DEFAULT uuid_generate_v4(),
    account_id UUID,
    operation_type VARCHAR(100),
    description TEXT,
    amount BIGINT DEFAULT 0,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT PK_operation PRIMARY KEY (id),
    CONSTRAINT FK_account_for_operation FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE RESTRICT
);