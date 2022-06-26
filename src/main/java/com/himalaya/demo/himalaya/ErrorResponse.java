package com.himalaya.demo.himalaya;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

    private int status;
    private String message;
    private List<FieldError> errors;
    private LocalDateTime createdAt;
    private String code;

    private ErrorResponse(int status, String message, List<FieldError> errors, LocalDateTime createdAt, String code) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.createdAt = createdAt;
        this.code = code;
    }

    private ErrorResponse(ErrorCode errorCode, List<FieldError> of) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.errors = of;
        this.createdAt = LocalDateTime.now();
        this.code = errorCode.getCode();
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.getStatus(),
                errorCode.getMessage(),
                new ArrayList<>(),
                LocalDateTime.now(),
                errorCode.getCode());
    }

    public static ErrorResponse of(Exception e) {
        return new ErrorResponse(
                500,
                e.getMessage(),
                new ArrayList<>(),
                LocalDateTime.now(),
                "C001"
                );
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ErrorResponse(errorCode, FieldError.of(bindingResult));
    }


    @Getter
    private static class FieldError {
        private String field;
        private String value;
        private String message;

        private FieldError(String field, String value, String message) {
            this.field = field;
            this.value = value;
            this.message = message;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());

        }
    }

}
