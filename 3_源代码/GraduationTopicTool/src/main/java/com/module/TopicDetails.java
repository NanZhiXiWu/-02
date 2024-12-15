package com.module;

import java.util.Date;

public class TopicDetails{
    private String selectionMethod;
    private String creatorId;
    private Date creationTime;
    private String topicStatus;
    private String departmentName;
    private String sourceOfTopic;
    private String researchDirection;
    private String theoryAndTechRequirements;

    public TopicDetails() {
    }

    public TopicDetails(String selectionMethod, String creatorId, Date creationTime, String topicStatus, String departmentName, String sourceOfTopic, String researchDirection, String theoryAndTechRequirements) {
        this.selectionMethod = selectionMethod;
        this.creatorId = creatorId;
        this.creationTime = creationTime;
        this.topicStatus = topicStatus;
        this.departmentName = departmentName;
        this.sourceOfTopic = sourceOfTopic;
        this.researchDirection = researchDirection;
        this.theoryAndTechRequirements = theoryAndTechRequirements;
    }

    public String getSelectionMethod() {
        return selectionMethod;
    }

    public void setSelectionMethod(String selectionMethod) {
        this.selectionMethod = selectionMethod;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(String topicStatus) {
        this.topicStatus = topicStatus;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getSourceOfTopic() {
        return sourceOfTopic;
    }

    public void setSourceOfTopic(String sourceOfTopic) {
        this.sourceOfTopic = sourceOfTopic;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    public String getTheoryAndTechRequirements() {
        return theoryAndTechRequirements;
    }

    public void setTheoryAndTechRequirements(String theoryAndTechRequirements) {
        this.theoryAndTechRequirements = theoryAndTechRequirements;
    }
}