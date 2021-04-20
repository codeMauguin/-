package com.white.pojo;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author chen hao
 */
public class ClassTable implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private long classId;
    private String className;
    @NotNull
    private long teaId;
    private long classYear;
    private long classTerm;
    private int stuNum;
    private int scoreValue;

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public int getStuNum() {
        return stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    public long getClassId() {
        return classId;
    }

    public final void setClassId(long classId) {
        this.classId = classId;
    }


    public final String getClassName() {
        return className;
    }

    public final void setClassName(String className) {
        this.className = className;
    }


    public final long getTeaId() {
        return teaId;
    }

    public final void setTeaId(long teaId) {
        this.teaId = teaId;
    }


    public final long getClassYear() {
        return classYear;
    }

    public final void setClassYear(long classYear) {
        this.classYear = classYear;
    }


    public final long getClassTerm() {
        return classTerm;
    }

    public final void setClassTerm(long classTerm) {
        this.classTerm = classTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClassTable that = (ClassTable) o;

        if (getClassId() != that.getClassId()) {
            return false;
        }
        return getClassName() != null ? getClassName().equals(that.getClassName()) : that.getClassName() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getClassId() ^ (getClassId() >>> 32));
        result = 31 * result + (getClassName() != null ? getClassName().hashCode() : 0);
        return result;
    }
}
