package com.himalaya.demo.himalaya;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(404, "C002", "invalid input value"),
    SERVER_ERROR(500, "C001", "internal server error"),
    DUPLICATED_MEMBER_ID(404, "C003", "duplicated member id");

    private int status;
    private String code;
    private String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
