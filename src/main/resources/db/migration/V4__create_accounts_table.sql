
CREATE TABLE IF NOT EXISTS accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    tenant_id BIGINT NOT NULL REFERENCES tenants(id),
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by BIGINT NOT NULL,
    updated_by BIGINT NOT NULL,
    request_id UUID NOT NULL,
    version BIGINT NOT NULL DEFAULT 0
);

CREATE UNIQUE INDEX IF NOT EXISTS uc_accounts_name_unique ON accounts(tenant_id, name);

-- Enable Row Level Security
ALTER TABLE accounts ENABLE ROW LEVEL SECURITY;

-- Create RLS policy to filter rows based on current_tenant_id
CREATE POLICY tenant_isolation_policy ON accounts
    USING (tenant_id = current_setting('app.current_tenant_id', true)::BIGINT);
