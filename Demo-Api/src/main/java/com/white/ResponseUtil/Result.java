package com.white.ResponseUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/24 星期二 9:33
 */
public class Result implements Serializable {
    @Serial
    private static final long serialVersionUID = 2463304126523514280L;
    private final Map<String, Object> data;
    private final String timeStamp;
    private Integer code;
    private Boolean isSuc;
    private String msg;

    private Result() {
        this.data = new Hashtable<>();
        this.timeStamp=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 默认请求成功
     *
     * @return 结果
     */
    public static Result ok() {
        final Result result = new Result();
        result.isSuc = true;
        result.code = ResultCustom.success.getCode();
        result.msg = ResultCustom.success.getMsg();
        return result;
    }

    public static Result err(Integer code, String msg) {
        final Result result = new Result();
        result.isSuc = false;
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static Result err(Integer code) {
        final Result result = new Result();
        result.isSuc = false;
        result.code = code;
        return result;
    }

    public static Result err(ResultCustom custom) {
        final Result result = new Result();
        result.isSuc = false;
        result.code = custom.getCode();
        result.msg = custom.getMsg();
        return result;
    }

    public static Result err() {
        final Result result = new Result();
        result.isSuc = false;
        result.code = 1000;
        return result;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Result data(String name, Object data) {
        this.data.put(name, data);
        return this;
    }

    public Result code(Integer code) {
        this.code = code;
        return this;
    }

    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Boolean getIsSuc() {
        return isSuc;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

}
