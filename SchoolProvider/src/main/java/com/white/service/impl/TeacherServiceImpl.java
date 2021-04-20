package com.white.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whit.SocketResultApi.DataResultApi;
import com.whit.password.IPassWordUtil;
import com.white.DButil.LoggerUtil;
import com.white.ResponseUtil.Result;
import com.white.dao.TeacherDao;
import com.white.pojo.ClassTable;
import com.white.pojo.Count;
import com.white.pojo.Score;
import com.white.pojo.Student;
import com.white.service.ITeacherService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/2 星期一 19:58
 */
@Service
@Transactional(rollbackFor = {SQLException.class,Exception.class})
public class TeacherServiceImpl implements ITeacherService {
    private final IPassWordUtil passwordUtil = IPassWordUtil.getInstance();
    @Resource
    private TeacherDao dao;

    @Override
    public Boolean addStudent(final Student student) throws Exception {
        // 1.添加学生
        final boolean b1 = this.dao.insertStudent(student);
        // 2.给学生设定初始密码
        if (b1) {
            final Count count = new Count();
            count.setCountId(student.getStuId());
            count.setCountSalt(this.passwordUtil.getSalt());
            count.setCountPwd(this.passwordUtil.getPwd(String.valueOf(student.getStuId()), count.getCountSalt()));
            count.setCountInfo(1);
            return this.dao.insertStudentCount(count);
        }
        return false;
    }

    @Override
    public Boolean addStudentClass(final long teaId, final long stuId, final long classId) throws Exception {
            return this.dao.insertStuClass(stuId, classId, teaId);
    }

    @Override
    public DataResultApi queryStudent(final long teaId, final long classId, final Integer current, final Integer pageSize) {
        try {
            final PageInfo<List<HashMap<String, Object>>> stringObjectMap =
                    PageHelper.startPage(current, pageSize).doSelectPageInfo(() -> this.dao.queryStudent(teaId, classId));
            return DataResultApi.resultSuc(200).msg("查询成功").build("list", stringObjectMap);
        } catch (final TooManyResultsException e) {
            LoggerUtil.logger(this.getClass()).error("错误信息:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<ClassTable> queryClassName(final long teaId) {
        return this.dao.queryClassName(teaId);
    }

    @Override
    public Result queryStuClass(final Long teaId) {// TODO: 2020/11/23 23:53 数据转换 修改数据会使Mybatis 缓存脏数据  ---使用readOnly-true
        // TODO: 2020/11/24 8:42 最终修改 使用clone 实现深度复制
        List<Student> students = this.dao.queryFuc1(teaId);
        final List<ClassTable> classTables = queryClassName(teaId);
        students = students.parallelStream()
                .map(e -> {
                    try {
                        final Student clone = e.clone();
                        clone.setClassTable(classTables.parallelStream()
                                .filter(c -> !e.getClassTable().contains(c))
                                .collect(toList()));
                        return clone;
                    } catch (final CloneNotSupportedException cloneNotSupportedException) {
                        cloneNotSupportedException.printStackTrace();
                    }
                    return e;
                })
                .collect(toList());
        System.gc();
        return Result.ok().data("list", students).data("len", classTables.size());
    }

    // TODO: 2020/11/23 23:55 第一次尝试使用 forEach 使数据转换为理想状态
    /*
 newResult.forEach(x -> x.setClassTable(classTables.stream()
                .filter(e -> !x.getClassTable().contains(e))
                .collect(toList())));
     */
    // TODO: 2020/11/24 0:00 第二次 修改数据
    /*
  for (Student student : students) {
            try {
   // TODO: 2020/11/23 23:57 使用 实现 Cloneable 使数据可以深度克隆

                newResult.add(student.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

      final List<Student> collect = newResult.stream()
                .peek(m -> m.setClassTable(
                        classTables.stream()
                                .filter(c -> !m.getClassTable().contains(c))
                                .collect(toList())
                ))
                .collect(toList());
     */


    @Override
    public List<Student> queryScore(final long teaId) {
        return this.dao.queryStuScoreIsJoinTea(teaId);
    }

    @Override
    public Boolean addScore(final DataResultApi api) {
        final Score score = new Score();
        score.setClassId((long) api.getData().get("classId"));
        score.setScoreValue((Long) api.getData().get("scoreValue"));
        score.setStuId((Long) api.getData().get("stuId"));
        score.setTeaId((long) api.getData().get("teaId"));
        return this.dao.insetScore(score);
    }

    @Override
    public Boolean addClass(final ClassTable t) {
        return this.dao.insertClass(t);
    }

    @Override
    public List<ClassTable> queryClass(final long teaId) {
        final List<ClassTable> classTables = this.dao.queryClass(teaId);
        return classTables.stream().peek(e -> {
            final int i = this.dao.queryStuSum(e.getClassId());
            e.setStuNum(i);
        }).collect(toList());
    }

    @Override
    public DataResultApi queryScoreList(final long teaId, final Integer pageNum, final Integer pageSize) {
        final PageInfo<List<Student>> objectPageInfo =
                PageHelper.startPage(pageNum, pageSize, "s.stu_id asc").doSelectPageInfo(() -> this.dao.queryScore(teaId));
        return DataResultApi.resultSuc(200)
                .build("list", objectPageInfo);
    }

    @Override
    public boolean updateScore(final Score score) {
        return this.dao.updateScore(score);
    }

}
