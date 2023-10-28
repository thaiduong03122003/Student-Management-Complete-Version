package com.nhom02.stumanager.model;

import java.io.Serializable;

public class Major implements Serializable {
    private String majorId, majorName, majorPhone, majorLink, eduProgId;

    public Major() {
    }

    public Major(String majorId, String majorName, String majorPhone, String majorLink, String eduProgId) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.majorPhone = majorPhone;
        this.majorLink = majorLink;
        this.eduProgId = eduProgId;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorPhone() {
        return majorPhone;
    }

    public void setMajorPhone(String majorPhone) {
        this.majorPhone = majorPhone;
    }

    public String getMajorLink() {
        return majorLink;
    }

    public void setMajorLink(String majorLink) {
        this.majorLink = majorLink;
    }

    public String getEduProgId() {
        return eduProgId;
    }

    public void setEduProgId(String eduProgId) {
        this.eduProgId = eduProgId;
    }
}
