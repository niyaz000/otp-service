
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    email VARCHAR(64),
    phone_number VARCHAR(10),
    tenant_id BIGINT NOT NULL REFERENCES tenants(id),
    account_id BIGINT NOT NULL REFERENCES accounts(id),
    tags JSONB DEFAULT '{}' NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by BIGINT NOT NULL,
    updated_by BIGINT NOT NULL,
    request_id UUID NOT NULL,
    version BIGINT NOT NULL DEFAULT 0
);
