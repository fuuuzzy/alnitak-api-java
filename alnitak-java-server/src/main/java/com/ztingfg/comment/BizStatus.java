package com.ztingfg.comment;

import org.springframework.http.HttpStatus;

/**
 * code > 1000 < Int.max, 业务异常
 * 1000-1009, 用户相关异常
 * 1010-1019, 用户相关异常
 * code < 0, 表示服务异常
 */
public enum BizStatus {

    complete(HttpStatus.OK, 200, "complete"),

    IllegalToken(HttpStatus.BAD_REQUEST, 2, "illegal token"),

    IllegalParams(HttpStatus.BAD_REQUEST, 10, "illegal params"),

    TokenCreateError(HttpStatus.INTERNAL_SERVER_ERROR, 900, "令牌创建失败"),

    TokenExpiration(HttpStatus.PAYMENT_REQUIRED, 903, "expiration token"),

    AccountExists(HttpStatus.BAD_REQUEST, 1000, "帐户已存在"),

    AccountNotExists(HttpStatus.INTERNAL_SERVER_ERROR, 1002, "帐户不存在"),

    AccountPasswordNotMatch(HttpStatus.INTERNAL_SERVER_ERROR, 1003, "密码不匹配"),

    AccountNotSignIn(HttpStatus.INTERNAL_SERVER_ERROR, 1004, "帐户未登陆"),

    ServerError(HttpStatus.INTERNAL_SERVER_ERROR, -1, "服务器异常"),

    mkdirDirectoryError(HttpStatus.INTERNAL_SERVER_ERROR, 2003, "创建文件夹失败"),

    SendMailCodeError(HttpStatus.INTERNAL_SERVER_ERROR, 2013, "60秒内只能发送一次验证码"),

    MailCodeError(HttpStatus.INTERNAL_SERVER_ERROR, 2014, "验证码不存在或者错误"),

    FilenameNotExists(HttpStatus.BAD_REQUEST, 3001, "文件名不存在"),

    FileUploadError(HttpStatus.BAD_REQUEST, 3003, "文件上传失败"),

    PartitionExists(HttpStatus.BAD_REQUEST, 3002, "分区已存在");

    private final HttpStatus httpStatus;

    private final int code;

    private final String message;

    BizStatus(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
