CREATE TABLE IF NOT EXISTS templates (
    id SERIAL PRIMARY KEY,
    tenant_id BIGINT NOT NULL REFERENCES tenants(id),
    account_id BIGINT NOT NULL REFERENCES accounts(id),
    name VARCHAR(64) NOT NULL,
    content VARCHAR(256) NOT NULL,
    description VARCHAR(512) DEFAULT NULL,
    status VARCHAR(16) NOT NULL DEFAULT 'DRAFT',
    type VARCHAR(16) NOT NULL,
    placeholders JSONB DEFAULT '[]',
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by BIGINT NOT NULL,
    updated_by BIGINT NOT NULL,
    request_id UUID NOT NULL,
    version BIGINT NOT NULL DEFAULT 0
);

CREATE UNIQUE INDEX IF NOT EXISTS uc_templates_account_id_name ON templates(account_id, name)
WHERE
    active = true;

-- Enable Row Level Security
ALTER TABLE
    templates ENABLE ROW LEVEL SECURITY;

-- Create RLS policy to filter rows based on current_account_id
CREATE POLICY tenant_isolation_policy ON templates USING (
    account_id = current_setting('app.current_account_id', false) :: BIGINT
);