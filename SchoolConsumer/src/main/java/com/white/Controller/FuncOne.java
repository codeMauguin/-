package com.white.Controller;

import com.whit.SocketResultApi.DataResultApi;
import com.white.Service.StudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/25 19:31
 */
@CrossOrigin
@RestController
@Validated
public class FuncOne {
    @Resource
    private StudentService service;

    /**
     * 查询分数
     *
     * @param stuId     学号
     * @param classYear 学年
     * @param className 课程名称
     * @param classTerm 学期
     * @param pageNum   页码
     * @param pageSize  页面大小
     * @return 结果
     */
    @GetMapping("/stu-role/selectScore")
    DataResultApi selectScore(@NotNull(message = "学号不允许为空") Long stuId,
                              Integer classYear,
                              Integer classTerm,
                              Integer pageNum,
                              Integer pageSize,
                              String className) {
        final Map<String, Object> map = new HashMap<>(4);
        map.put("stu_id", stuId);
        map.put("class_year", classYear);
        map.put("class_term", classTerm);
        map.put("class_name", className.toCharArray());
        return service.selectScore(map, pageNum, pageSize);
    }
}
