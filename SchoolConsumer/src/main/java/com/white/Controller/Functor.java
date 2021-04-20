package com.white.Controller;

import com.whit.SocketResultApi.DataResultApi;
import com.white.ResponseUtil.Result;
import com.white.Service.TeacherService;
import com.white.pojo.ClassTable;
import com.white.pojo.Score;
import com.white.pojo.Student;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/27 1:28
 */
@CrossOrigin
@RestController
@Validated
public class Functor  {
    @Resource
    private TeacherService service;

    /**
     * 插入学生
     *
     * @param student 学生信息
     * @return 结果
     */
    @PostMapping("/tea-role/insertStu")
    Result insertStu(Student student){
        Result result;
        try {
            final boolean b = service.addStudent(student);
            if (b) {
                result = Result.ok().msg("添加用户成功");
            } else {
                result = Result.err(300, "添加失败");
            }
        } catch (Exception e) {
            result = Result.err(301, "已存在该学生，请勿重复添加");
        }
        return result;
    }

    /**
     * 插入学生班级
     *
     * @param teaId   教师id
     * @param stuId   学生Id
     * @param classId 班级Id
     * @return 结果
     */
    @PostMapping("/tea-role/insertStuClass")
    Result insertStuClass(@NotNull(message = "添加教师Id不能为空") long teaId,
                          @NotNull(message = "学生Id不能为空") long stuId,
                          @NotNull(message =
                                  "课程Id不能为空") long classId){
        final boolean b = service.addStudentClass(teaId, stuId, classId);
        if (b) {
            return Result.ok().msg("添加课程成功");
        } else {
            return Result.err(300, "添加失败");
        }
    }

    /**
     * 教师查询学生
     *
     * @param teaId       教师编号
     * @param classId     课程编号
     * @param pageCurrent 页码
     * @param pageSize    页面大小
     * @return 学生集合
     */
    @GetMapping("/tea-role/queryStudent")
    DataResultApi queryStudent(@NotNull(message = "查询教师名不允许为空") long teaId,
                               long classId, Integer pageCurrent,
                               Integer pageSize) {
        return service.queryStudent(teaId, classId, pageCurrent, pageSize);
    }

    /**
     * 查询课程名称
     *
     * @param teaId 教师Id
     * @return 课程集合
     */
    @GetMapping("/tea-role/queryClass")
    Result queryClassName(long teaId){
        final List<ClassTable> strings = service.queryClassName(teaId);
        return Result.ok().data("list", strings);
    }

    /**
     * =====
     *
     * @param teaId 教师Id
     * @return 结果
     */
    @GetMapping("/tea-role/queryStuClass")
    Result queryStuClass(Long teaId) {
        return service.queryStuClass(teaId);
    }

    /**
     * 查询学生未有成绩的课程
     *
     * @param teaId 教师编号
     * @return 所有没有成绩的集合
     */
    @GetMapping("/tea-role/queryScore")
    Result queryStuScore(Long teaId){
        final List<Student> students = service.queryScore(teaId);
        return Result.ok().data("score", students);
    }


    /**
     * 添加成绩
     *
     * @param score 学生分数信息
     * @return 成绩是否添加成功
     */
    @PostMapping("/tea-role/insertScore")
    Result insertScore(Score score){
        final DataResultApi data = DataResultApi.resultSuc().data("teaId", score.getTeaId())
                .data("classId", score.getClassId())
                .data("scoreValue", score.getScoreValue())
                .data("stuId", score.getStuId());
        final Boolean aBoolean = service.addScore(data);
        if (aBoolean) {
            return Result.ok().msg("添加成功");
        } else {
            return Result.err().msg("添加失败");
        }

    }

    /**
     * 添加成绩
     *
     * @param classTable 课程信息
     * @return 添加结果
     */
    @PostMapping("/tea-role/insertClass")
    Result insertClass(ClassTable classTable){
        final Boolean aBoolean = service.addClass(classTable);
        if (aBoolean) {
            return Result.ok()
                    .msg("添加成功");
        } else {
            return Result
                    .err(400, "添加失败");
        }
    }

    /**
     * 查询教师课程信息
     *
     * @param teaId 教师编号
     * @return 结果
     */
    @GetMapping("/tea-role/queryTeaClass")
    Result queryClass(@NotNull(message = "教师编号不允许为空") long teaId) {
        final List<ClassTable> classTables = service.queryClass(teaId);
        return Result.ok()
                .data("list", classTables);
    }

    /**
     * 查询可修改分数
     *
     * @param teaId    教师编号
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return list
     */
    @GetMapping("/tea-role/queryScoreList")
    DataResultApi queryScoreList(long teaId, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            pageNum = pageNum == null ? 1 : pageNum;
            pageSize = pageSize == null ? 5 : pageSize;
        }
        return service.queryScoreList(teaId, pageNum, pageSize);
    }

    /**
     * 更新分数
     *
     * @param score 分数信息
     * @return 插入结果
     */
    @PatchMapping("/tea-role/updateScore")
    Result updateScore(Score score){
        final boolean b = service.updateScore(score);
        if (b) {
            return Result.ok().msg("更新成功");
        } else {
            return Result.err(100000).msg("更新失败");
        }
    }

}
