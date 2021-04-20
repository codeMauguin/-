package com.white.dao;

import com.white.pojo.Count;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/4 星期三 14:39
 */
public interface ICountDao {
    /**
     * 用户登录
     *
     * @param countId 学号
     * @return n
     */
    @Select("select count_salt, count_pwd, count_info   from [diplomaProject ].dbo.count   where count_id = #{countId}")
    Count userLogin(long countId);

    /**
     * 查询用户权限
     *
     * @param countId 用户学号
     * @return 权限集合
     */
    @Select(" select count_info from [diplomaProject ].dbo.count where count_id = #{countId}")
    Integer userInfo(Long countId);

    /**
     * 修改密码
     * @param newPwd 新密码
     * @param newSalt 新盐
     * @param userName 用户名
     * @return 修改结果
     * @throws Exception 异常
     */
    @Update("update dbo.count set count_salt=#{newSalt},count_pwd=#{newPwd} where count_id=#{userName}")
    Boolean userChangePwd(String newPwd,String newSalt,Long userName)throws Exception;
}
