package com.white.service;

import com.whit.SocketResultApi.DataResultApi;

import java.util.Map;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/1 星期日 0:53
 */
public interface IStudentService {
    /**
     * 查询成绩
     *
     * @param hashMap  条件
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return 结果
     */
    DataResultApi selectScore(Map<String, Object> hashMap, int pageNum, int pageSize);

}
