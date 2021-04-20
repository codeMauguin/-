package com.white.security;

import com.white.ErrorMannage.LoginException;
import com.white.Jwt.JwtTokenUtil;
import com.white.Redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/26 星期四 13:17
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Resource
    private JwtTokenUtil util;
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = request.getHeader(util.getHeader());
        String userName = util.getUserNameFromToken(token);
        if (redisUtil.hasKey(userName)) {
            final Map<Object, Object> meet = redisUtil.hmget(userName);
            final String o =(String) meet.get(LoginSuccessHandler.TOKEN_KEY);
            if (!o.equals(token)) {
                resolver.resolveException(request, response, null, new LoginException("登录信息过期"));
            } else {
                // TODO: 2020/11/27 0:38 删除用户信息应先将数据提交到数据库中
                meet.put(LoginSuccessHandler.IS_LOGIN_KEY, false);
                redisUtil.hmset(userName, meet);
                String redirectUrl = "/countInfo/logout";
                request.getRequestDispatcher(redirectUrl).forward(request, response);
            }
        } else {
            resolver.resolveException(request, response, null, new LoginException("登录信息过期"));
        }
    }
}
