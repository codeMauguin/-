package com.white.Cache;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 陈浩
 * @version 2021/2/8 22:38
 * @cread Talk is cheap. Show me the code
 */
public class MybatisRedisCache implements Cache {
    private final String id;
    private final Logger logger=LoggerFactory.getLogger(getClass());
    public MybatisRedisCache(String id) {
        this.id = id;
        logger.info("当前加入缓存的namespace:{}",id);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        logger.info("key的值为:{}",o);
        logger.info("value为:{}",o1);
    }

    @Override
    public Object getObject(Object o) {
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
