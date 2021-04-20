package com.white.pojo;


import java.io.Serial;
import java.io.Serializable;

/**
 * @author chen hao
 */
public class Teacher implements Serializable {

    @Serial
    private static final long serialVersionUID = 6382910075565827246L;
    private long teaId;
    private String teaName;


    public long getTeaId() {
        return teaId;
    }

    public void setTeaId(long teaId) {
        this.teaId = teaId;
    }


    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    @Override
    public String toString() {
        return "Teacher{" +
               "teaId=" + teaId +
               ", teaName='" + teaName + '\'' +
               '}';
    }
}
