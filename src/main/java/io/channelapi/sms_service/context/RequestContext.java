package io.channelapi.sms_service.context;

import io.channelapi.sms_service.entity.Account;
import io.channelapi.sms_service.entity.Tenant;
import io.sentry.util.Objects;

public class RequestContext {

    private Tenant tenant;

    private Account account;

    public RequestContext(Account account) {
        this.tenant = Objects.requireNonNull(account.getTenant(), "Account's tenant cannot be null");
        this.account = account;
    }

    public Integer getTenantId() {
        return tenant.getId();
    }

    public Integer getAccountId() {
        return account.getId();
    }
}
