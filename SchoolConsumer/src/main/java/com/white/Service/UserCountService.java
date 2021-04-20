package com.white.Service;

import com.whit.Socket.Annotation.SocketServer;
import com.white.ResponseUtil.Result;

/**
 * @author ???
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/26 11:53
 */
@SocketServer
public interface UserCountService {


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
     */
    Boolean  userChangePwd(Long userName,String oldPwd,String newPwd);
}
