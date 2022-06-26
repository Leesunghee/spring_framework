package com.himalaya.demo.himalaya.exception;

import com.himalaya.demo.himalaya.ErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
