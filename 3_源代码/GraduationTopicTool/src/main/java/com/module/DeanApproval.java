package com.module;

public class DeanApproval {
    private Dean dean;
    private String selectionRequirements;
    private String researchDirection;
    private String status;
    private String approvalComments;

    public DeanApproval() {
    }

    public DeanApproval(Dean dean, String selectionRequirements, String researchDirection, String status, String approvalComments) {
        this.dean = dean;
        this.selectionRequirements = selectionRequirements;
        this.researchDirection = researchDirection;
        this.status = status;
        this.approvalComments = approvalComments;
    }

    public Dean getDean() {
        return dean;
    }

    public void setDean(Dean dean) {
        this.dean = dean;
    }

    public String getSelectionRequirements() {
        return selectionRequirements;
    }

    public void setSelectionRequirements(String selectionRequirements) {
        this.selectionRequirements = selectionRequirements;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalComments() {
        return approvalComments;
    }

    public void setApprovalComments(String approvalComments) {
        this.approvalComments = approvalComments;
    }
}