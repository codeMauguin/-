package com.white.security;

import com.white.DButil.List.IListUtil;
import com.white.DButil.LoggerUtil;
import com.white.Jwt.JwtTokenUtil;
import com.white.Redis.RedisUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/24 星期二 10:46
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    protected final static String TOKEN_KEY = "token";
    public final static String IP_KEY = "userLoginIp";
    public final static String TIME_KEY = "userLoginTime";
    public final static String IS_LOGIN_KEY = "isLogin";
    public final static String ROLE_KEY = "role";
    @Resource
    private JwtTokenUtil util;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private IListUtil listUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String token = request.getHeader(util.getHeader());
        String loginUserName = authentication.getName();
        String errorKey = "ERROR";
        if (Objects.nonNull(token) && !errorKey.equals(token)) {
            String userName = util.getUserNameFromToken(token);
            if (Objects.equals(userName, loginUserName)) {
                if (util.isTokenExpired(token)) {
                    LoggerUtil.logger(this.getClass()).info("用户令牌过期，已刷新");
                    token = util.redRushToken(token);
                }
            } else {
                LoggerUtil.logger(this.getClass()).error("用户登录名为:{}请求token中用户名为:{}", loginUserName, userName);
                LoggerUtil.logger(this.getClass()).info("用户携带令牌不合法，已经重置");
                token = util.generateToken(loginUserName);
            }
        } else {
            // TODO: 2020/11/26 12:40 若多地登录实现，及在这取出 服务器存放的TOKEN 使每一个登录用户共用一个TOKEN
            // TODO: 2020/11/27 9:02 若要下线某人，删除ip 即可
            LoggerUtil.logger(this.getClass()).info(":{}用户未携带令牌登录，已新建令牌", loginUserName);
            token = util.generateToken(loginUserName);
        }
        Map<Object, Object> userInfo;
        final List<String> o;
        if (redisUtil.hasKey(loginUserName)) {
            userInfo = redisUtil.hmget(loginUserName);
            o = listUtil.caseList(userInfo.get(IP_KEY), String.class);
            // TODO: 2020/11/27 10:55  应该存放ip对应的登录时间，退出修改改时间即可
            // 登录中如果包含，应该讲Ip 取出放在list末尾
            o.remove(request.getRemoteAddr());
        } else {
            userInfo = new HashMap<>(5);
            o = new ArrayList<>();
        }
        o.add(request.getRemoteAddr());
        userInfo.put(IP_KEY, o);
        userInfo.put(TOKEN_KEY, token);
        userInfo.put(TIME_KEY, LocalDateTime.now());
        userInfo.put(IS_LOGIN_KEY, true);
        final Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role = String.valueOf(authorities.toArray()[0]);
        userInfo.put(ROLE_KEY, role);
        redisUtil.hmset(loginUserName, userInfo);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(token, null, authorities));
        final String redirectUrl = "/login/success";
        request.getRequestDispatcher(redirectUrl).forward(request, response);
    }
}
