package com.entity;

public class Apply {
    private String name;
    private String sno;
    private String classno;
    private String date;
    private String reason;
    private String course;
    private String teacher;
    private String instructor;
    private int no;
    private String right;

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", sno='" + sno + '\'' +
                ", classno='" + classno + '\'' +
                ", date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                ", course='" + course + '\'' +
                ", teacher='" + teacher + '\'' +
                ", instructor='" + instructor + '\'' +
                ", right='" + right + '\'' +
                '}';
    }
}
