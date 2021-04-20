package com.white.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whit.SocketResultApi.DataResultApi;
import com.white.dao.StudentDao;
import com.white.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/1 星期日 0:53
 * //@Transactional(rollbackFor = Exception.class)
 */
@Service
public class StudentServiceImpl implements IStudentService {
    @Resource
    private StudentDao dao;

    @Override
    public DataResultApi selectScore(final Map<String, Object> hashMap, final int pageNum, final int pageSize) {
        final PageInfo<Map<String, Object>> hashMapPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.dao.selectScore(hashMap));
        return DataResultApi.resultSuc(200).build("list", hashMapPageInfo).msg("查询成功");
    }

}
