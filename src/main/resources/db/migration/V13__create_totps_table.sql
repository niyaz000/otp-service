
CREATE TABLE IF NOT EXISTS totps (
    id SERIAL PRIMARY KEY,
    secret_key VARCHAR(64) NOT NULL,
    period INT NOT NULL,
    digits INT NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users(id),

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
