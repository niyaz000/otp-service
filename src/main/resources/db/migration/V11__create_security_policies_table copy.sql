
CREATE TABLE IF NOT EXISTS security_policies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(256),
    ipv4_address VARCHAR(64) NOT NULL,
    ipv6_address VARCHAR(64) NOT NULL,
    action VARCHAR(16) NOT NULL,

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
