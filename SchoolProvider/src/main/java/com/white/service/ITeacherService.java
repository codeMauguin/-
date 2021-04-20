package com.white.service;

import com.whit.SocketResultApi.DataResultApi;
import com.white.ResponseUtil.Result;
import com.white.pojo.ClassTable;
import com.white.pojo.Score;
import com.white.pojo.Student;

import java.util.List;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/2 星期一 19:58
 */
public interface ITeacherService {
    /**
     * 老师添加学生
     *
     * @param student 学生信息
     * @return 是否添加成功
     * @throws Exception 捕获异常
     */
    Boolean addStudent(Student student) throws Exception;

    /**
     * 给学生添加课程
     *
     * @param teaId   教师Id
     * @param stuId   学生Id
     * @param classId 课程Id
     * @return 结果
     * @throws Exception 异常
     */
    Boolean addStudentClass(long teaId, long stuId, long classId)throws Exception;

    /**
     * 查询数据
     *
     * @param teaId    教师ID
     * @param classId  班级Id
     * @param current  页码
     * @param pageSize 页面大小
     * @return 分页后集合
     */
    DataResultApi queryStudent(long teaId, long classId, Integer current, Integer pageSize);

    /**
     * 查询老师下的课程
     *
     * @param teaId 教师Id
     * @return 学生集合
     */
    List<ClassTable> queryClassName(long teaId);

    /**
     * 查询 学生没有加入老师的课程
     *
     * @param teaId 教师号
     * @return 学生集合
     */
    Result queryStuClass(Long teaId);

    /**
     * 查询学生是否添加过成绩
     *
     * @param teaId 教师编号
     * @return 集合
     */
    List<Student> queryScore(long teaId);

    /**
     * 添加成绩
     *
     * @param score 分数信息
     * @return 插入是否成功
     */
    Boolean addScore(DataResultApi score);

    /**
     * 添加课程
     *
     * @param t 课程信息
     * @return 插入结果
     */
    Boolean addClass(ClassTable t);

    /**
     * 教师查询自己的课程
     *
     * @param teaId 教师编号
     * @return 课表
     */
    List<ClassTable> queryClass(long teaId);


    /**
     * 查询数，已加入老师课程的
     *
     * @param teaId    教师编号
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return 查询结果
     */
    DataResultApi queryScoreList(long teaId, Integer pageNum, Integer pageSize);

    /**
     * 更新分数
     *
     * @param score 分数信息
     * @return 插入结果
     */
    boolean updateScore(Score score);
}
