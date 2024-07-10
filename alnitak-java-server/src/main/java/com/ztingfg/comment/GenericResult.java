package com.ztingfg.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResult<T> {

    private String msg;

    private Integer code;

    private T data;

    private String traceId;

    public static GenericResult<Object> from(BizStatus status) {
        return GenericResult.builder()
                .code(status.getCode())
                .msg(status.getMessage())
                .traceId(RequestContext.getTracerId()).build();
    }

    public static GenericResult<Object> from(BizStatus status, String message) {
        return GenericResult.builder()
                .code(status.getCode())
                .msg(message)
                .traceId(RequestContext.getTracerId()).build();
    }

    public static GenericResult<Object> fail(BizStatus status) {
        return GenericResult.from(status);
    }

    public static GenericResult<Object> success() {
        return GenericResult.from(BizStatus.complete);
    }

    @SuppressWarnings("unchecked")
    public static <T> GenericResult<T> success(T data) {
        return (GenericResult<T>) GenericResult.builder()
                .code(BizStatus.complete.getCode())
                .msg(BizStatus.complete.getMessage())
                .traceId(RequestContext.getTracerId())
                .data(data)
                .build();
    }
}
