package com.white.service.impl;

import com.whit.password.IPassWordUtil;
import com.white.RSA.RsaUtil;
import com.white.Redis.RedisUtil;
import com.white.ResponseUtil.Result;
import com.white.dao.ICountDao;
import com.white.pojo.Count;
import com.white.service.ICount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/**
 * @author 陈浩
 * @cread   Talk is cheap,show me the code
 * @date 2020-11-17 11:50:42
 * @Last Modified by: 陈浩
 * @Last Modified time: 2020-11-17 12:04:36
 */
@Service
@Transactional(rollbackFor = {SQLException.class,Exception.class})
public class UserCountServiceImpl implements ICount {
    private final IPassWordUtil passwordUtil = IPassWordUtil.getInstance();
    @Resource
    private ICountDao dao;
    @Resource
    private RsaUtil util;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public Result login(final Long userName) {
        final String saltUri = "salt";
        final String pwdUri = "pwd";
        final String countInfoUri = "info";
        final Count count = this.dao.userLogin(userName);
        return Result.ok()
                .data(saltUri, count.getCountSalt())
                .data(pwdUri, count.getCountPwd())
                .data(countInfoUri, count.getCountInfo());
    }

    @Override
    public Boolean userChangePwd(final Long userName, String oldPwd, String newPwd) throws Exception {
        final AtomicReference<Map<Object, Object>> get = new AtomicReference<>(this.redisUtil.hmget(userName + ":key"));
        final String privateKey = String.valueOf(get.get().get(RsaUtil.PRIVATE_KEY));
        this.redisUtil.del(userName + ":key");
        oldPwd = this.util.decrypt(oldPwd, privateKey);
        if (oldPwd != null) {
            final Result login = login(userName);
            final String saltUri = "salt";
            final String pwdUri = "pwd";
            final String pwd = this.passwordUtil.getPwd(oldPwd, (String) login.getData().get(saltUri));
            if (this.passwordUtil.matches(pwd, (String) login.getData().get(pwdUri))) {
                newPwd = this.util.decrypt(newPwd, privateKey);
                if (newPwd != null) {
                    final String newSalt = this.passwordUtil.getSalt();
                    newPwd = this.passwordUtil.getPwd(newPwd, newSalt);
                    return this.dao.userChangePwd(newPwd, newSalt, userName);
                }
            }
        }
        return false;
    }
}