package com.white.Service;

import com.whit.Socket.Annotation.SocketServer;
import com.white.pojo.Teacher;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/26 20:12
 */
@SocketServer
public interface CountService {

    /**
     * 添加老师
     *
     * @param teacher 教师信息
     * @return 添加结果
     * @throws Exception 异常信息
     */
    boolean addTea(Teacher teacher) throws Exception;

    /**
     * 查询用户名
     *
     * @param id 用户账号
     * @return 用户名
     */
    String queryName(Long id);
}
