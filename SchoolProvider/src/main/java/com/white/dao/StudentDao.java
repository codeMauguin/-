package com.white.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/1 星期日 0:38
 */
public interface StudentDao {
    /**
     * 查询成绩
     *
     * @param hashMap 条件
     * @return 结果集
     */
    List<HashMap<String, Object>> selectScore(Map<String, Object> hashMap);
}
