package com.himalaya.demo.himalaya;

import com.himalaya.demo.himalaya.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class SimpleControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity(ErrorResponse.of(e), HttpStatus.valueOf(ErrorCode.SERVER_ERROR.getStatus()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.error(e.getMessage());
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity(ErrorResponse.of(errorCode), HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    public ResponseEntity<ErrorResponse> handleIllegalException(RuntimeException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE), HttpStatus.valueOf(ErrorCode.INVALID_INPUT_VALUE.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult()), HttpStatus.valueOf(ErrorCode.INVALID_INPUT_VALUE.getStatus()));
    }
}
