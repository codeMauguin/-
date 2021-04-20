package com.white.dao;

import com.white.pojo.ClassTable;
import com.white.pojo.Count;
import com.white.pojo.Score;
import com.white.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/2 星期一 20:25
 */
public interface TeacherDao {

    /**
     * 添加学生
     *
     * @param student 学生
     * @return 是否添加成功
     * @throws Exception 异常处理
     */
    boolean insertStudent(Student student) throws Exception;

    /**
     * 给学生添加账户
     *
     * @param count 学生账号信息
     * @return 结果
     * @throws Exception 异常处理
     */
    boolean insertStudentCount(Count count) throws Exception;

    /**
     * 给学生添加课程
     *
     * @param stuId   学生ID
     * @param classId 课程ID
     * @param teaId   教师ID
     * @return 插入是否成功
     * @throws Exception 异常
     */
    Boolean insertStuClass(@Param("stuId") long stuId,@Param("classId") long classId,
                           @Param("teaId") long teaId)throws Exception;

    /**
     * 查询学生
     *
     * @param teaId  老师Id
     * @param classId 课程
     * @return 学生信息
     */
    List<HashMap<String, Object>> queryStudent(@Param("tea_id") long teaId, @Param("classId") long classId);

    /**
     * 查询课程
     *
     * @param teaId 教师
     * @return 课程
     */
    List<ClassTable> queryClassName(long teaId);

    /**
     * 查询 学生课表
     *
     * @param teaId 教师Id
     * @return 学生类型集合
     */
    List<Student> queryFuc1(long teaId);

    /**
     * 第二个查询
     *
     * @param teaId 教师Id
     * @return 结果
     */
    List<Score> queryFuc2(long teaId);

    /**
     * 第二个查询
     *
     * @param teaId 教师Id
     * @return 结果
     */
    List<Student> queryStuScoreIsJoinTea(long teaId);

    /**
     * 插入学生成绩
     *
     * @param score 分数信息
     * @return 添加是否成功
     */
    Boolean insetScore(Score score);

    /**
     * 插入课程
     *
     * @param table 课程信息
     * @return 结果
     */
    Boolean insertClass(ClassTable table);

    /**
     * 教师查询个人编号
     *
     * @param teaId 教师编号
     * @return 课程
     */
    List<ClassTable> queryClass(long teaId);

    /**
     * 查询课程有多少已加入学生
     *
     * @param classId 课程Id
     * @return 学生数量
     */
    int queryStuSum(long classId);

    /**
     * 查询数，已加入老师课程的
     *
     * @param teaId 教师编号
     * @return 查询结果
     */
    List<Student> queryScore(long teaId);

    /**
     * 修改分数
     *
     * @param score 分数信息
     * @return 更新结果
     */
    boolean updateScore(Score score);
}
