package com.white.Service;

import com.whit.Socket.Annotation.SocketServer;
import com.whit.SocketResultApi.DataResultApi;

import java.util.Map;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/26 20:09
 */
@SocketServer
public interface StudentService {
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
