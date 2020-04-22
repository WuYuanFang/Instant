package com.kevin.instant.core.exception;

/**
 * @program: Instant
 * @create: 2020-04-21 22:59
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

public class DKException extends RuntimeException {

    public DKException(){}

    public DKException(String message) {
        super(message);
    }

    public DKException(String message, Throwable cause) {
        super(message, cause);
    }

    public DKException(Throwable cause) {
        super(cause);
    }

    public DKException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
