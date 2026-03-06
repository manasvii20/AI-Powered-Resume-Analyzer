package com.resumeai.model;

import java.time.LocalDateTime;

public class ResumeAnalysis {
    private int id;
    private String candidateName;
    private String jobTitle;
    private int matchPercentage;
    private String missingSkills; // Stored as a comma-separated string or JSON string
    private LocalDateTime timestamp;

    public ResumeAnalysis() {
    }

    public ResumeAnalysis(int id, String candidateName, String jobTitle, int matchPercentage, String missingSkills, LocalDateTime timestamp) {
        this.id = id;
        this.candidateName = candidateName;
        this.jobTitle = jobTitle;
        this.matchPercentage = matchPercentage;
        this.missingSkills = missingSkills;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(int matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public String getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(String missingSkills) {
        this.missingSkills = missingSkills;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
