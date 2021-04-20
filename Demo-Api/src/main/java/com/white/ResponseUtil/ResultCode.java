package com.white.ResponseUtil;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/24 星期二 9:37
 */
public interface ResultCode {
    /**
     * 返回提示信息
     *
     * @return 返回状态码
     */
    Integer getCode();

    /**
     * 返回提示信息
     *
     * @return 返回提示信息
     */
    String getMsg();
}
