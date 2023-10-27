package com.nhom02.stumanager.model;

public class Major {
    private String majorId;
    private  String majorName;
    private String majorLink;

    public Major() {
    }

    public Major(String majorId, String majorName, String majorLink) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.majorLink = majorLink;
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

    public String getMajorLink() {
        return majorLink;
    }

    public void setMajorLink(String majorLink) {
        this.majorLink = majorLink;
    }
}
