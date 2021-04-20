package com.white.dao;

import com.white.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/23 星期一 11:10
 */
public interface ICountAdminDao {
    /**
     * 添加老师
     *
     * @param teacher 教师信息
     * @return 结果
     * @throws Exception 抛出添加可能的异常
     */
    boolean addTea(Teacher teacher) throws Exception;

    /**
     * 查询用户信息
     * @param id 用户编号
     * @return 查询结果
     */
    String queryName(long id);
}
