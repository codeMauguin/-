package com.white.Controller;

import com.white.RSA.RsaUtil;
import com.white.Redis.RedisUtil;
import com.white.ResponseUtil.Result;
import com.white.ResponseUtil.ResultCustom;
import com.white.Service.CountService;
import com.white.pojo.Teacher;
import com.white.security.LoginSuccessHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/27 0:26
 */
@RestController
@CrossOrigin
@Validated
public class FuncTwo {
    @Resource
    private RedisUtil util;
    @Resource
    private RsaUtil rsaUtil;
    @Resource
   private CountService service;
    @PostMapping("/admin/addTea")
    public Result addTea(Teacher teacher) {
        final boolean b;
        try {
            b = service.addTea(teacher);
        } catch (Exception e) {
            return Result.err().msg(e.getMessage()).code(ResultCustom.EXCEPTION.getCode());
        }
        if (b) {
            return Result.ok().msg("添加成功");
        } else {
            return Result.err(600, "添加失败");
        }
    }
    @GetMapping("/countInfo/queryInfo")
    public Result queryCountInfo(long id) {
        String countId = String.valueOf(id);
        if (util.hasKey(countId)) {
            final Map<Object, Object> meet = util.hmget(countId);
            return Result.ok()
                    .data("userLastTime", meet.get(LoginSuccessHandler.TIME_KEY))
                    .data("userName", service.queryName(id))
                    .data("LoginIp", meet.get(LoginSuccessHandler.IP_KEY));
        } else {
            return Result.err(ResultCustom.LoginErrorException);
        }
    }
    @PostMapping("/countInfo/logout")
    public Result logout() {
        return Result.ok().msg("退出成功");
    }
    @PostMapping("/count/getPrivateKey")
    public Result getPublicKey(long id) throws NoSuchAlgorithmException {
        Map<Object, Object> key = rsaUtil.getKey();
        util.hmset(id + ":key", key);
        return Result.ok().data(RsaUtil.PUBLIC_KEY, key.get(RsaUtil.PUBLIC_KEY));
    }

}
