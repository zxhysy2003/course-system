package com.sy.course_system.vo;

public class LearningPathRecommendVO {
    private Long fromId;
    private String fromName;

    private Long toId;
    private String toName;

    private Long support;
    private String reason;

    public void buildReason() {
        this.reason = "在学习完《" + fromName + "》的用户中，有" + this.support + "人接下来学习了《" + toName + "》。";
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public Long getSupport() {
        return support;
    }

    public void setSupport(Long support) {
        this.support = support;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
