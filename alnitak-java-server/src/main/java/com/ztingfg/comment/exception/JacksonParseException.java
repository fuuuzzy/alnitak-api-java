package com.ztingfg.comment.exception;

public class JacksonParseException extends RuntimeException {

    public JacksonParseException() {
    }

    public JacksonParseException(String message) {
        super(message);
    }

    public JacksonParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public JacksonParseException(Throwable cause) {
        super(cause);
    }

    public JacksonParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
