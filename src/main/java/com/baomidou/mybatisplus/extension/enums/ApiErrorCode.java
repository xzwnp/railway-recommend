package com.baomidou.mybatisplus.extension.enums;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

/**
 * REST API 错误码
 *
 * @author hubin
 * @since 2017-06-26
 */
// 使用度较低，如果使用请及时迁移本地 3.5.0 移除
public enum ApiErrorCode implements IErrorCode {
    /**
     * 失败
     */
    FAILED(1000, "操作失败"),
    /**
     * 成功
     */
    SUCCESS(0, "执行成功"),

    /**
     * token过期
     */
    TOKEN_INVALID(1001, "登录过期或Token无效,请重新登录!"),
    NO_PERMISSION(1001, "您没有权限进行此操作!");

    private final long code;
    private final String msg;

    ApiErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiErrorCode fromCode(long code) {
        ApiErrorCode[] ecs = ApiErrorCode.values();
        for (ApiErrorCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }
}
