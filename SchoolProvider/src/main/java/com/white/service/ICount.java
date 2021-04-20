/**
 * @Author: 陈浩
 * @Creed: Talk is cheap,show me the code
 * @Date: 2020-11-17 11:49:35
 * @Last Modified by: 陈浩
 * @Last Modified time: 2020-11-17 12:06:34
 */
package com.white.service;

import com.white.ResponseUtil.Result;

public interface ICount {
    /**
     * 根据用户名查用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    Result login(Long userName);
    /**
     * 修改密码
     * @param userName 用户id
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 是否修改成功
     * @throws Exception 异常
     */
    Boolean  userChangePwd(Long userName,String oldPwd,String newPwd) throws Exception;
}
