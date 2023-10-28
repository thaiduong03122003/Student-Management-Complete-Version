package com.nhom02.stumanager.model;

public class EducationProgram {
    String eduId, eduName;

    public EducationProgram() {
    }

    public EducationProgram(String eduId, String eduName) {
        this.eduId = eduId;
        this.eduName = eduName;
    }

    public String getEduId() {
        return eduId;
    }

    public void setEduId(String eduId) {
        this.eduId = eduId;
    }

    public String getEduName() {
        return eduName;
    }

    public void setEduName(String eduName) {
        this.eduName = eduName;
    }
}
