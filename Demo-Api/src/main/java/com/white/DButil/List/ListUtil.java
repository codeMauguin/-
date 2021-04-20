package com.white.DButil.List;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/12/1 星期二 10:37
 */
@Component
public class ListUtil implements IListUtil {
    @Override
    public <T> List<T> caseList(Object src, Class<T> tClass) {
        List<T> result = new ArrayList<T>();
        if (src instanceof List<?>) {
            result = ((List<T>) src).stream().collect(Collectors.toCollection(Vector::new));
        }
        return result;
    }

}
