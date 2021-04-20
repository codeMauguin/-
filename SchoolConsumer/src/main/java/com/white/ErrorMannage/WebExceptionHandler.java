package com.white.ErrorMannage;

import com.white.ResponseUtil.Result;
import com.white.ResponseUtil.ResultCustom;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/2 星期一 21:09
 */
@RestControllerAdvice
public class WebExceptionHandler {
    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码

    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(BindException e) {
        e.printStackTrace();
        return Result.err(ResultCustom.PORT_EXCEPTION);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     *
     * @param e 错误
     * @return 错误信息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(ConstraintViolationException e) {
        return Result.err(ResultCustom.PARAMETER_FORMAT_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return Result.err(ResultCustom.METHODS_NOTNULL);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        return Result.err(ResultCustom.FOUR_NOTFOUND);
    }


    @ExceptionHandler(Exception.class)
    public Result baseException(Exception e) {
        return Result.err(ResultCustom.EXCEPTION).msg(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runTimeException(RuntimeException ex) {
        return Result.err(ResultCustom.RUNTIME_EXCEPTION.getCode()).msg(ex.getMessage());
    }

    @ExceptionHandler(LoginException.class)
    public Result loginException(LoginException ex) {
        return Result.err(ex.getCode()).msg(ex.getMessage());
    }
}
