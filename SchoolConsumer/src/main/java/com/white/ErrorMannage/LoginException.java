package com.white.ErrorMannage;

import java.io.Serial;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/25 星期三 21:53
 */
public class LoginException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5301682466799837593L;
    private final int CODE;
    public LoginException(String message) {
        super(message);
        CODE = 101;
    }
    public int getCode() {
        return CODE;
    }
}
