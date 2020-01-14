package com.after00.exception;

import lombok.*;

/**
 * 业务异常
 *
 * @author wangbiao*/
@Data
public class TestRuntimeException extends RuntimeException{

    public TestRuntimeException() {
    }

    public TestRuntimeException(String message) {
        super(message);
    }

    public TestRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestRuntimeException(Throwable cause) {
        super(cause);
    }

    public TestRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
