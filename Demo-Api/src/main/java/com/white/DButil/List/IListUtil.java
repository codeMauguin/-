package com.white.DButil.List;

import java.util.List;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/12/1 星期二 10:38
 */
public interface IListUtil {
    /**
     * 将Object 对象转换类型
     *
     * @param src    源需要转换的对象
     * @param tClass 转换目标的类型
     * @return 转换结果
     */
    <T> List<T> caseList(Object src, Class<T> tClass);
}
