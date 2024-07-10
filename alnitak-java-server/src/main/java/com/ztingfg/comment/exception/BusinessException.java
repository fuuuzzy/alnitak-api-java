package com.ztingfg.comment.exception;

import com.ztingfg.comment.BizStatus;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final BizStatus bizStatus;

    private final String innerMessage;

    public BusinessException(BizStatus bizStatus) {
        this(bizStatus, null);
    }

    public BusinessException(BizStatus bizStatus, String message) {
        super("businessMessage: " + bizStatus.getMessage() + ", innerMessage: " + message);
        this.bizStatus = bizStatus;
        this.innerMessage = message;
    }

    public BizStatus getBusinessStatus() {
        return bizStatus;
    }

    public int getCode() {
        return bizStatus.getCode();
    }

    public String getBusinessMessage() {
        return bizStatus.getMessage();
    }

}
