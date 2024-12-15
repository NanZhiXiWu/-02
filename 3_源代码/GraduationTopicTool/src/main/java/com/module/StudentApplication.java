package com.module;

public class StudentApplication {
    int topicId;
    String studentId;
    String studentName;
    String major;
    String phoneNumber;
    String topicName;
    String selectionMethod;
    String teacherName;
    String status;
    String approved;

    public StudentApplication(){}

    public StudentApplication(Students students,Topic topic ){
        topicId = topic.getTopicId();
        studentId = students.getId();
        major = students.getMajor();
        phoneNumber = students.getPhone();
        topicName = topic.getTopicName();
        selectionMethod = "教师出题";
        teacherName = topic.getTeacher().getCode();
        status = "undone";
        approved = "undone";
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getSelectionMethod() {
        return selectionMethod;
    }

    public void setSelectionMethod(String selectionMethod) {
        this.selectionMethod = selectionMethod;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getApproved() {
        return approved;
    }
}
