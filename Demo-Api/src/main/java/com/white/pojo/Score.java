package com.white.pojo;


import java.io.Serial;
import java.io.Serializable;

/**
 * @author chen hao
 */
public class Score implements Serializable {

    @Serial
    private static final long serialVersionUID = 4621318106574590668L;
    private long scoreId;
    private long classId;
    private long stuId;
    private long scoreValue;
    private long teaId;

    public long getTeaId() {
        return teaId;
    }

    public void setTeaId(long teaId) {
        this.teaId = teaId;
    }

    @Override
    public String toString() {
        return "Score{" +
               "scoreId=" + scoreId +
               ", classId=" + classId +
               ", stuId=" + stuId +
               ", scoreValue=" + scoreValue +
               '}';
    }

    public long getScoreId() {
        return scoreId;
    }

    public void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }


    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }


    public long getStuId() {
        return stuId;
    }

    public void setStuId(long stuId) {
        this.stuId = stuId;
    }


    public long getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(long scoreValue) {
        this.scoreValue = scoreValue;
    }

}
