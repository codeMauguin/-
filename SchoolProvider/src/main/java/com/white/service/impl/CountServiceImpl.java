package com.white.service.impl;

import com.whit.password.IPassWordUtil;
import com.white.dao.ICountAdminDao;
import com.white.dao.TeacherDao;
import com.white.pojo.Count;
import com.white.pojo.Teacher;
import com.white.service.ICountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/23 星期一 11:09
 */
@Service
@Transactional(rollbackFor = {SQLException.class,Exception.class})
public class CountServiceImpl implements ICountService {
    private final IPassWordUtil passwordUtil = IPassWordUtil.getInstance();
    @Resource
    private ICountAdminDao dao;
    @Resource
    private TeacherDao teacherDavao;

    @Override
    public boolean addTea(Teacher teacher) throws Exception {
        final boolean b = dao.addTea(teacher);
        if (b) {
            final Count count = new Count();
            count.setCountId(teacher.getTeaId());
            count.setCountSalt(passwordUtil.getSalt());
            count.setCountPwd(passwordUtil.getPwd(String.valueOf(teacher.getTeaId()), count.getCountSalt()));
            count.setCountInfo(2);
            return teacherDavao.insertStudentCount(count);
        }
        return false;
    }

    @Override
    public String queryName(Long id) {
        try {
            final String s = dao.queryName(id);
            return s;
        }catch (Exception e){
          e.printStackTrace();
        }
        return null;
    }
}
