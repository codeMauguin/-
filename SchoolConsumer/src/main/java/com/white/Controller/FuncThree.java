package com.white.Controller;

import com.white.ResponseUtil.Result;
import com.white.ResponseUtil.ResultCustom;
import com.white.Service.UserCountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/26 11:55
 */
@RestController
@CrossOrigin
@Validated
public class FuncThree {
    @Resource
   private UserCountService service;

    /**
     * 用户登录的接口
     *
     * @return 登录成功
     */
    @PostMapping(value = "/login/success")
    Result userLoginSuc(){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities();
        Object role = authorities.toArray()[0];
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Result.ok().data("token", principal
        ).data("role", role);
    }
    /**
     * 用户登录的接口
     *
     * @return 登录失败
     */
    @PostMapping(value = "/login/error")
    Result userLoginErr(){
        return Result.err(ResultCustom.Login_error.getCode(), ResultCustom.Login_error.getMsg());
    }

    /**
     * 处理404
     *
     * @param code 错误码
     * @return 处理404
     */
    @RequestMapping("/resourceNotFound")
    Result resourceNotFound(int code){
        return Result.err(code).msg("资源没有找到");
    }

    /**
     * 用户异地登录处理
     *
     * @return 用户异地登录异常
     */
    @RequestMapping("/logerror")
    Result userRemoteLogin(){
        return Result.err(ResultCustom.LoginErrorException);
    }

    /**
     * 修改密码
     * @param userName 用户id
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 是否修改成功
     */
    @PatchMapping("countInfo/userChangePwd")
    Result userChangePwd(Long userName,String oldPwd,String newPwd){
        final Boolean result = service.userChangePwd(userName, oldPwd, newPwd);
        if(result){
            return Result.ok().msg("修改成功");
        }else {
            return Result.err().msg("修改失败");
        }    }

}
