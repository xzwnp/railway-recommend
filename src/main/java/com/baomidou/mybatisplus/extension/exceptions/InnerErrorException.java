package com.baomidou.mybatisplus.extension.exceptions;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

/**
 * REST API 请求异常类
 *
 * @author hubin
 * @since 2017-06-26
 */
// 使用度较低，如果使用请及时迁移本地 3.5.0 移除
public class InnerErrorException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5885155226898287919L;

    /**
     * 错误码
     */
    private IErrorCode errorCode;

    public InnerErrorException(IErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public InnerErrorException(IErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public InnerErrorException(String message) {
        super(message);
    }

    public InnerErrorException(Throwable cause) {
        super(cause);
    }

    public InnerErrorException(String message, Throwable cause) {
        super(message, cause);
    }


    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
