CREATE TABLE IF NOT EXISTS tenants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    status VARCHAR(16) NOT NULL DEFAULT 'UNVERIFIED',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by BIGINT NOT NULL,
    updated_by BIGINT NOT NULL,
    request_id UUID NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    email VARCHAR(64) NOT NULL,
    phone_number VARCHAR(10) NOT NULL,
    country_code VARCHAR(5) NOT NULL DEFAULT 'IN',
    email_verified BOOLEAN NOT NULL DEFAULT false,
    phone_number_verified BOOLEAN NOT NULL DEFAULT false
);

CREATE UNIQUE INDEX IF NOT EXISTS uc_tenants_email_unique ON tenants(email);

CREATE UNIQUE INDEX IF NOT EXISTS uc_tenants_phone_number_unique ON tenants(phone_number);

CREATE UNIQUE INDEX IF NOT EXISTS uc_tenants_name ON tenants(name);

-- Enable Row Level Security
ALTER TABLE
    tenants ENABLE ROW LEVEL SECURITY;

-- Create RLS policy to filter rows based on current_tenant_id
CREATE POLICY tenant_isolation_policy ON tenants USING (
    id = current_setting('app.current_tenant_id', false) :: BIGINT
);