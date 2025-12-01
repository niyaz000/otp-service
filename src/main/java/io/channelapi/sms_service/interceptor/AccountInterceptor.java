package io.channelapi.sms_service.interceptor;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.channelapi.sms_service.constants.ApiConstants;
import io.channelapi.sms_service.constants.LoggerConstants;
import io.channelapi.sms_service.context.RequestContext;
import io.channelapi.sms_service.context.RequestContextHolder;
import io.channelapi.sms_service.exception.ApiErrorResponse.FieldError;
import io.channelapi.sms_service.repository.AccountRepository;
import io.channelapi.sms_service.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountInterceptor implements HandlerInterceptor {

    private final AccountRepository accountRepository;

    private final ResponseUtil responseUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        RequestContextHolder.clearContext();
        var accountIdHeader = Objects.requireNonNullElse(request.getHeader("X-Account-ID"), "");
        if (accountIdHeader.isBlank()) {
            var error = new FieldError(LoggerConstants.X_ACCOUNT_ID, accountIdHeader, ApiConstants.MISSING_HEADER, "X-Account-ID header is required.");
            responseUtil.handleValidationError(request, error, response);
            return false;
        }
        Long accountId;
        try {
            accountId = Long.parseLong(accountIdHeader);
        } catch (NumberFormatException e) {
            var error = new FieldError(LoggerConstants.X_ACCOUNT_ID, accountIdHeader, ApiConstants.INVALID_HEADER, "X-Account-ID header must be a valid number.");
            responseUtil.handleValidationError(request, error, response);
            return false;
        }
        var account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            var error = new FieldError(LoggerConstants.X_ACCOUNT_ID, accountIdHeader, "invalid_account", "Account not found for the given X-Account-ID.");
            responseUtil.handleValidationError(request, error, response);
            return false;
        }
        var tenantIdHeader = Objects.requireNonNullElse(request.getHeader(LoggerConstants.X_TENANT_ID), "");
        if (tenantIdHeader.isBlank()) {
            var error = new FieldError(LoggerConstants.X_TENANT_ID, tenantIdHeader, ApiConstants.MISSING_HEADER, "X-Tenant-ID header is required.");
            responseUtil.handleValidationError(request, error, response);
            return false;
        }
        Integer tenantId;
        try {
            tenantId = Integer.parseInt(tenantIdHeader);
        } catch (NumberFormatException e) {
            var error = new FieldError(LoggerConstants.X_TENANT_ID, tenantIdHeader, ApiConstants.INVALID_HEADER, "X-Tenant-ID header must be a valid number.");
            responseUtil.handleValidationError(request, error, response);
            return false;
        }
        if (!account.get().getTenantId().equals(tenantId)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        if (!account.get().isActive()) {
            var error = new FieldError(LoggerConstants.X_ACCOUNT_ID, accountIdHeader, "deleted_account", "The account associated with the given X-Account-ID has been deleted.");
            responseUtil.handleValidationError(request, error, response);
            return false;
        }
        if (!account.get().getTenant().isActive()) {
            var error = new FieldError(LoggerConstants.X_TENANT_ID, tenantIdHeader, "deleted_tenant", "The tenant associated with the given X-Tenant-ID has been deleted.");
            responseUtil.handleValidationError(request, error, response);
            return false;
        }        
        if (!account.get().getTenantId().equals(tenantId)) {
            var error = new FieldError(LoggerConstants.X_TENANT_ID, tenantIdHeader, "invalid_tenant", "The tenant ID does not match the account's tenant.");
            responseUtil.handleValidationError(request, error, response);
            return false;
        }
        RequestContextHolder.setContext(new RequestContext(account.get()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           org.springframework.web.servlet.ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
    }
}
