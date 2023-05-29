package com.baomidou.mybatisplus.extension.api;

/**
 * REST API 错误码接口
 *
 * @author hubin
 * @since 2018-06-05
 */
public interface IErrorCode {

    /**
     * 错误编码 0:成功 非0:失败
     */
    long getCode();

    /**
     * 错误描述
     */
    String getMsg();
}
