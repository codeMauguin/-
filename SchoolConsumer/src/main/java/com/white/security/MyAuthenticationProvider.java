package com.white.security;

import com.whit.password.IPassWordUtil;
import com.white.DButil.LoggerUtil;
import com.white.RSA.RsaUtil;
import com.white.Redis.RedisUtil;
import com.white.ResponseUtil.Result;
import com.white.Service.UserCountService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020-11-16 00:24:08
 * @Last Modified by: 陈浩
 * @Last Modified time: 2020-11-17 13:27:11
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    private final IPassWordUtil passwordUtil = IPassWordUtil.getInstance();
    @Resource
    private RsaUtil util;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserCountService dao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String saltUri = "salt";
        final String pwdUri = "pwd";
        final String countInfoUri = "info";
        long userName = Long.parseLong(authentication.getName());
        String userPwd = (String) authentication.getCredentials();
        AtomicReference<Map<Object, Object>> get = new AtomicReference<>(redisUtil.hmget(userName + ":key"));
        String privateKey = String.valueOf(get.get().get(RsaUtil.PRIVATE_KEY));
        redisUtil.del(userName + ":key");
        userPwd = util.decrypt(userPwd, privateKey);
        /*
          从数据库查询数据
         */
        final Result count = dao.login(userName);
        if (ObjectUtils.isEmpty(count) || ObjectUtils.isEmpty(userPwd)) {
            throw new BadCredentialsException("用户不存在");
        } else {
            final String newPassword = passwordUtil.getPwd(userPwd, (String) count.getData().get(saltUri));
            /*
              校验用户密码是否正确
             */
            if (authentication.getCredentials() == null) {
                LoggerUtil.logger(this.getClass()).info("用户名已查到->传入密码为空");
                throw new BadCredentialsException("登录名或密码错误");
            } else if (!this.passwordUtil.matches((String) count.getData().get(pwdUri), newPassword)) {
                LoggerUtil.logger(this.getClass()).info("用户名已查到->密码校验后错误");
                throw new BadCredentialsException("登录名或密码错误");
            } else {
                LoggerUtil.logger(this.getClass()).info("用户名已查到->校验正确->:{}权限为:{}",
                        userName,
                        count.getData().get(countInfoUri));
                String role = switch ((Integer) count.getData().get(countInfoUri)) {
                    case 1 -> "STU_ROLE";
                    case 2 -> "TEA_ROLE";
                    case 3 -> "ADMIN_ROLE";
                    default -> "ERR_ROLE";
                };
                return new UsernamePasswordAuthenticationToken(userName, null,
                        AuthorityUtils.createAuthorityList(role));
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
