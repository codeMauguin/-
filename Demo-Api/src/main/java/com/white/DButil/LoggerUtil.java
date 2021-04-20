package com.white.DButil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author 陈浩
 * @cread Talk is cheap,show me the code
 * @date  2020-11-17 12:15:07
 * @Last Modified by: 陈浩
 * @Last Modified time: 2020-11-17 17:10:05
 */
public class LoggerUtil {
    public static Logger logger(final Class<?> t) {
        return LoggerFactory.getLogger(t);
    }
}
