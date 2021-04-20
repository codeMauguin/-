package com.white.pojo;


import java.io.Serial;
import java.io.Serializable;

/**
 * @author chen hao
 */
public class Count implements Serializable {

    @Serial
    private static final long serialVersionUID = 7804269175243683938L;
    private transient long countId;
    private String countSalt;
    private transient String countPwd;
    private transient Integer countInfo;

    public long getCountId() {
        return countId;
    }

    public void setCountId(long countId) {
        this.countId = countId;
    }

    public String getCountSalt() {
        return countSalt;
    }

    public void setCountSalt(String salt) {
        this.countSalt = salt;
    }

    public String getCountPwd() {
        return countPwd;
    }

    public void setCountPwd(String countPwd) {
        this.countPwd = countPwd;
    }

    public Integer getCountInfo() {
        return countInfo;
    }

    public void setCountInfo(Integer countInfo) {
        this.countInfo = countInfo;
    }

    @Override
    public String toString() {
        return "Count{" +
               "countId=" + countId +
               ", countSalt='" + countSalt + '\'' +
               ", countPwd='" + countPwd + '\'' +
               ", countInfo=" + countInfo +
               '}';
    }
}
