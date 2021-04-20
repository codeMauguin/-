package com.white.ResponseUtil;
/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/24 星期二 9:38
 */
public enum ResultCustom implements ResultCode {
    /**
     * 登录成功
     */
    success(200, "请求成功"),
    error(600, "请求失败"),
    EXCEPTION(100, "程序出错，请联系管理员修复"),
    METHODS_NOTNULL(700, "参数不能为空"),
    PERMISSION_DENIED(302, "没有权限"),
    FOUR_NOTFOUND(404, "请输入正确路径"),
    PARAMETER_FORMAT_ERROR(770, "参数格式错误"),
    PORT_EXCEPTION(900, "端口错误"),
    RUNTIME_EXCEPTION(800, "运行错误"),
    EXIT_LOGOUT(201, "退出成功"),
    /*
    状态是101 即代表用户多地登录
     */
    LoginErrorException(101, "异地登录请重新登录"),
    /**
     * 登录失败
     */
    Login_error(401, "账户或者密码错误");
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 返回信息
     */
    private final String msg;

    ResultCustom(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
