package io.channelapi.sms_service.utils;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.channelapi.sms_service.constants.ApiConstants;
import io.channelapi.sms_service.exception.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class ResponseUtil {

    private final JsonUtil jsonUtil;

    public void handleValidationError(
            HttpServletRequest request,
            ApiErrorResponse.FieldError error,
            HttpServletResponse response) {
        var responseBody = toValidationErrorBuilder(request);
        responseBody.errors(List.of(error));
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writeResponse(response, responseBody.build());
    }

    private ApiErrorResponse.ApiErrorResponseBuilder toResponseBuilder(int status) {
        return ApiErrorResponse.builder()
                .requestId(LoggerUtil.currentRequestId())
                .timestamp(Date.from(Instant.now()))
                .status(status);
    }

    public ApiErrorResponse.ApiErrorResponseBuilder toValidationErrorBuilder(HttpServletRequest request) {
        var builder = toResponseBuilder(HttpServletResponse.SC_BAD_REQUEST);
        builder
                .type(ApiConstants.VALIDATION_ERROR)
                .detail("One or more validation errors occurred.")
                .title("Validation Error")
                .instance(request.getServletPath().replace("/v1", ""));
        return builder;
    }

    @SneakyThrows
    public void writeResponse(HttpServletResponse response,
            ApiErrorResponse responseBody) {
        response.setStatus(responseBody.getStatus());
        response.getWriter().write(jsonUtil.toJsonString(responseBody));
    }
}
