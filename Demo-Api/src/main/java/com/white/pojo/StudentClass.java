package com.white.pojo;


import java.io.Serial;
import java.io.Serializable;

/**
 * @author chen hao
 */
public class StudentClass implements Serializable {

    @Serial
    private static final long serialVersionUID = -4899411857009912174L;
    private long stuId;
    private long classId;

    @Override
    public String toString() {
        return "StudentClass{" +
               "stuId=" + stuId +
               ", classId=" + classId +
               '}';
    }

    public long getStuId() {
        return stuId;
    }

    public void setStuId(long stuId) {
        this.stuId = stuId;
    }


    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

}
