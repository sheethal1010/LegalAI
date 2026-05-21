package com.legalai.app.models;

public class LegalCase {
    public static final int STATUS_SUBMITTED = 0;
    public static final int STATUS_AI_PROCESSING = 1;
    public static final int STATUS_LAWYER_REVIEW = 2;
    public static final int STATUS_APPROVED = 3;
    public static final int STATUS_DELIVERED = 4;

    private String id;
    private String title;
    private String type;
    private String description;
    private int status;
    private String agentUsed;
    private String assignedLawyer;
    private long timestamp;

    public LegalCase() {}

    public LegalCase(String id, String title, String type, String description,
                     int status, String agentUsed, String assignedLawyer, long timestamp) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.status = status;
        this.agentUsed = agentUsed;
        this.assignedLawyer = assignedLawyer;
        this.timestamp = timestamp;
    }

    public String getStatusLabel() {
        switch (status) {
            case STATUS_SUBMITTED: return "Submitted";
            case STATUS_AI_PROCESSING: return "AI Processing";
            case STATUS_LAWYER_REVIEW: return "In Review";
            case STATUS_APPROVED: return "Approved";
            case STATUS_DELIVERED: return "Delivered";
            default: return "Unknown";
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getAgentUsed() { return agentUsed; }
    public void setAgentUsed(String agentUsed) { this.agentUsed = agentUsed; }
    public String getAssignedLawyer() { return assignedLawyer; }
    public void setAssignedLawyer(String assignedLawyer) { this.assignedLawyer = assignedLawyer; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
