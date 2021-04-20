package com.white.pojo;


import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author chen hao
 */
public class Student implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 5765676549897636653L;
    @NotNull(message = "学号不允许为空")
    private long stuId;
    @NotNull(message = "学生姓名不允许为空")
    private String stuName;
    @NotNull(message = "学生班级不为空")
    private String stuClass;
    private List<ClassTable> classTable;

    @Override
    public Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }

    public List<ClassTable> getClassTable() {
        return classTable;
    }

    public void setClassTable(List<ClassTable> classTable) {
        this.classTable = classTable;
    }

    public long getStuId() {
        return stuId;
    }

    public void setStuId(long stuId) {
        this.stuId = stuId;
    }


    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }


    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    @Override
    public String toString() {
        return "Student{" +
               "stuId=" + stuId +
               ", stuName='" + stuName + '\'' +
               ", stuClass='" + stuClass + '\'' +
               ", classTable=" + classTable +
               '}';
    }
}
