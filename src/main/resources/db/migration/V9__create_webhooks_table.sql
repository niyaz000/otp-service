
CREATE TABLE IF NOT EXISTS webhooks (
    id SERIAL PRIMARY KEY,
    url VARCHAR(128) NOT NULL,
    http_method VARCHAR(16) NOT NULL,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(256),
    events JSONB DEFAULT '[]' NOT NULL,
    headers JSONB DEFAULT '{}' NOT NULL,

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
