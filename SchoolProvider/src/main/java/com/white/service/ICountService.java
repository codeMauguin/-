package com.white.service;

import com.white.pojo.Teacher;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/23 星期一 11:09
 */
public interface ICountService {
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
