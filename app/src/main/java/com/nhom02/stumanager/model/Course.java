package com.nhom02.stumanager.model;

public class Course {
    private String courseId, courseName, schYear, majorId;

    public Course(){
    }

    public Course(String courseName, String schYear) {
        this.courseName = courseName;
        this.schYear = schYear;
    }

    public Course(String courseId, String courseName, String schYear, String majorId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.schYear = schYear;
        this.majorId = majorId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSchYear() {
        return schYear;
    }

    public void setSchYear(String schYear) {
        this.schYear = schYear;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }
}
