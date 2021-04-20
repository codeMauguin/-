package com.white.Config;

import com.white.DButil.LoggerUtil;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/6 19:32
 */
@Component
@Scope
public class MyHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LoggerUtil.logger(this.getClass()).info("请求地址path:[{}],uri:[{}]",request.getServletPath(),request.getRequestURI());
        LoggerUtil.logger(this.getClass()).info("请求的方法处理器为:[{}]",handler.toString());
        String header = request.getHeader("user-agent");
        LoggerFactory.getLogger(this.getClass()).info("用户Ip:{}",request.getRemoteAddr());
        LoggerUtil.logger(this.getClass()).info("用户的登录端是->:{}",header);
        return true;
    }
}
