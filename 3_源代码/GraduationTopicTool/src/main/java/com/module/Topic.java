package com.module;

public class Topic {
    private int topicId;
    private String topicName;
    private String topicDescription;
    private Teacher teacher;
    private DeanApproval deanApproval;

    public Topic() {
    }

    public Topic(int topicId, String topicName, String topicDescription, Teacher teacher, DeanApproval deanApproval) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.topicDescription = topicDescription;
        this.teacher = teacher;
        this.deanApproval = deanApproval;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public DeanApproval getDeanApproval() {
        return deanApproval;
    }

    public void setDeanApproval(DeanApproval deanApproval) {
        this.deanApproval = deanApproval;
    }
}