package com.white.security;

import com.white.DButil.List.IListUtil;
import com.white.Jwt.JwtTokenUtil;
import com.white.Redis.RedisUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/27 0:52
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private JwtTokenUtil util;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private IListUtil listUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO: 2020/11/23 15:29  权限查询优化  (个人觉得查询在判断是否网页需要权限进行查询)
        final String loginPath = "/login";
        final String key = "/count/getPrivateKey";
        if (!Objects.equals(request.getServletPath(), loginPath) && !Objects.equals(request.getServletPath(), key)) {
            String token = request.getHeader(util.getHeader());
            if (!ObjectUtils.isEmpty(token)) {
                String userName = util.getUserNameFromToken(token);
                if (Objects.nonNull(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // TODO: 2020/11/25 21:50 注入用户信息查询 --用户token不一致或者，ip不一致，上一个登录者再次操作时会被踢下线
                    if (redisUtil.hasKey(userName)) {
                        final Map<Object, Object> meet = redisUtil.hmget(userName);
                        String o = (String) meet.get(LoginSuccessHandler.TOKEN_KEY);
                        final List<String> o1 = listUtil.caseList(meet.get(LoginSuccessHandler.IP_KEY), String.class);
                        if (!o.equals(token) || !String.valueOf(o1.get(o1.size() - 1)).equals(request.getRemoteAddr())) {
//                            // TODO: 2020/12/6 如果用户携带的验证令牌与服务器的令牌不一致则异地登录或Ip与用户最后登录记录不一致
                            final String redirectUrl = "/logerror";
                            request.getRequestDispatcher(redirectUrl).forward(request, response);
                            return;
                        } else {
                            final String role = String.valueOf(meet.get(LoginSuccessHandler.ROLE_KEY));
                            if (util.validateToken(token, userName)) {
                                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                        null, null, AuthorityUtils.createAuthorityList(role));
                                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                                filterChain.doFilter(request, response);
                                return;
                            }
                        }
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
