
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    email VARCHAR(64),
    phone_number VARCHAR(10),
    password_hash VARCHAR(256) NOT NULL,
    external_id VARCHAR(40) NOT NULL,
    role VARCHAR(16) NOT NULL,

    tenant_id BIGINT NOT NULL REFERENCES tenants(id),
    account_id BIGINT NOT NULL REFERENCES accounts(id),
    tags JSONB DEFAULT '{}' NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by BIGINT NOT NULL REFERENCES users(id),
    updated_by BIGINT NOT NULL REFERENCES users(id),
    request_id UUID NOT NULL,
    version BIGINT NOT NULL DEFAULT 0
);
