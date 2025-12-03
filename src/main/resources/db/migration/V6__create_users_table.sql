CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    email VARCHAR(64),
    email_verified_at TIMESTAMPTZ DEFAULT NULL,
    phone_number VARCHAR(10),
    phone_verified_at TIMESTAMPTZ DEFAULT NULL,
    tenant_id BIGINT NOT NULL REFERENCES tenants(id),
    account_id BIGINT NOT NULL REFERENCES accounts(id),
    tags JSONB DEFAULT '{}' NOT NULL,
    password_hash VARCHAR(512) DEFAULT NULL,
    external_id VARCHAR(40) DEFAULT NULL,
    roles JSONB NOT NULL DEFAULT '[]',
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by BIGINT NOT NULL,
    updated_by BIGINT NOT NULL,
    request_id UUID NOT NULL,
    version BIGINT NOT NULL DEFAULT 0
);

CREATE UNIQUE INDEX IF NOT EXISTS uc_users_external_id ON users(external_id);

-- Enable Row Level Security
ALTER TABLE
    users ENABLE ROW LEVEL SECURITY;

-- Create RLS policy to filter rows based on current_account_id
CREATE POLICY users_tenant_isolation_policy ON users USING (
    account_id = current_setting('app.current_account_id', false) :: BIGINT
);