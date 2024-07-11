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

    Processing(HttpStatus.CONFLICT, 1, "processing"),

    IllegalToken(HttpStatus.BAD_REQUEST, 2, "illegal token"),

    ParamsMissing(HttpStatus.BAD_REQUEST, 3, "missing params"),

    IllegalParams(HttpStatus.BAD_REQUEST, 10, "illegal params"),

    TokenCreateError(HttpStatus.INTERNAL_SERVER_ERROR, 900, "令牌创建失败"),

    TokenDecodeError(HttpStatus.INTERNAL_SERVER_ERROR, 901, "令牌解析失败"),

    TokenExpiration(HttpStatus.PAYMENT_REQUIRED, 903, "expiration token"),

    AccountExists(HttpStatus.BAD_REQUEST, 1000, "帐户已存在"),

    AccountRegisterFault(HttpStatus.BAD_REQUEST, 1001, "注册失败"),

    AccountNotExists(HttpStatus.INTERNAL_SERVER_ERROR, 1002, "帐户不存在"),

    AccountPasswordNotMatch(HttpStatus.INTERNAL_SERVER_ERROR, 1003, "密码不匹配"),

    AccountNotSignIn(HttpStatus.INTERNAL_SERVER_ERROR, 1004, "帐户未登陆"),

    AccountNotAdmin(HttpStatus.FORBIDDEN, 1005, "帐户不是管理员"),

    AccountCreateError(HttpStatus.INTERNAL_SERVER_ERROR, 1006, "创建帐户失败"),

    AccountCreateSessionError(HttpStatus.INTERNAL_SERVER_ERROR, 1007, "创建会话失败"),

    TenantNotFound(HttpStatus.NOT_FOUND, 1010, "组织不存在"),

    TenantCreateError(HttpStatus.INTERNAL_SERVER_ERROR, 1011, "创建组织失败"),

    OldPasswordNotMatch(HttpStatus.INTERNAL_SERVER_ERROR, 1012, "旧密码不匹配"),

    PasswordUpdateError(HttpStatus.INTERNAL_SERVER_ERROR, 1013, "密码修改失败，请稍后重试"),

    CodeNotMatch(HttpStatus.INTERNAL_SERVER_ERROR, 1014, "2FA Code 错误"),

    LimitConsumeError(HttpStatus.PAYMENT_REQUIRED, 1015, "该租户已经没有额度"),

    LimitExpiryConsumeError(HttpStatus.PAYMENT_REQUIRED, 1017, "该租户已经超过缓冲期"),

    LimitConsumeNoSuccess(HttpStatus.CONFLICT, 1016, "数据发生变化，消耗没有成功，请重试"),

    ServerError(HttpStatus.INTERNAL_SERVER_ERROR, -1, "服务器异常"),

    TenantError(HttpStatus.INTERNAL_SERVER_ERROR, 2001, "操作组织异常"),

    mkdirDirectoryError(HttpStatus.INTERNAL_SERVER_ERROR, 2003, "创建文件夹失败"),

    FBError(HttpStatus.INTERNAL_SERVER_ERROR, 2004, "操作facebook异常"),

    GgentError(HttpStatus.INTERNAL_SERVER_ERROR, 2005, "操作代理商异常"),

    AppGroupError(HttpStatus.INTERNAL_SERVER_ERROR, 2007, "操作分组异常"),

    EventError(HttpStatus.INTERNAL_SERVER_ERROR, 2008, "操作事件异常"),

    MenuError(HttpStatus.INTERNAL_SERVER_ERROR, 2010, "操作菜单异常"),

    RoleError(HttpStatus.INTERNAL_SERVER_ERROR, 2011, "操作角色异常"),

    PermissionError(HttpStatus.INTERNAL_SERVER_ERROR, 2012, "操作许可异常"),

    SendMailCodeError(HttpStatus.INTERNAL_SERVER_ERROR, 2013, "60秒内只能发送一次验证码"),

    MailCodeError(HttpStatus.INTERNAL_SERVER_ERROR, 2014, "验证码不存在或者错误"),

    FacebookGroupError(HttpStatus.INTERNAL_SERVER_ERROR, 2015, "操作facebook帐户组异常"),

    RequestServerError(HttpStatus.INTERNAL_SERVER_ERROR, 2009, "请求服务器错误"),

    RequestBpServerError(HttpStatus.INTERNAL_SERVER_ERROR, 2010, "BePower数据异常"),

    FacebookStateNotMatch(HttpStatus.BAD_REQUEST, 3000, "facebook callback state not match"),

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
