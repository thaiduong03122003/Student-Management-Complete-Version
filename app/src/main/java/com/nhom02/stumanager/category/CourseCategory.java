package com.nhom02.stumanager.category;

import com.nhom02.stumanager.model.Course;

import java.util.List;

public class CourseCategory {
    private String courseCateName;
    private List<Course> courseList;

    public CourseCategory(String courseCateName, List<Course> courseList) {
        this.courseCateName = courseCateName;
        this.courseList = courseList;
    }

    public String getCourseCateName() {
        return courseCateName;
    }

    public void setCourseCateName(String courseCateName) {
        this.courseCateName = courseCateName;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
