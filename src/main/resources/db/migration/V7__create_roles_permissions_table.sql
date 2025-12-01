
CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(512) NOT NULL,
    is_default BOOLEAN NOT NULL DEFAULT false,
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

CREATE UNIQUE INDEX IF NOT EXISTS uc_roles_name ON roles(account_id, name) WHERE active = true AND is_default = false;

CREATE TABLE IF NOT EXISTS permissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(512) NOT NULL,
    is_default BOOLEAN NOT NULL DEFAULT false,
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

CREATE UNIQUE INDEX IF NOT EXISTS uc_permissions_name ON permissions(account_id, name) WHERE active = true AND is_default = false;

CREATE TABLE IF NOT EXISTS role_permissions (
    id SERIAL PRIMARY KEY,
    role_id BIGINT NOT NULL REFERENCES roles(id),
    permission_id BIGINT NOT NULL REFERENCES permissions(id),
    tenant_id BIGINT NOT NULL REFERENCES tenants(id),
    account_id BIGINT NOT NULL REFERENCES accounts(id),
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by BIGINT NOT NULL REFERENCES users(id),
    updated_by BIGINT NOT NULL REFERENCES users(id),
    request_id UUID NOT NULL,
    version BIGINT NOT NULL DEFAULT 0
);

CREATE UNIQUE INDEX IF NOT EXISTS uc_role_permissions_unique ON role_permissions(account_id, role_id, permission_id) WHERE active = true;
