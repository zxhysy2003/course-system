package com.sy.course_system.vo;

import java.util.List;

public class CourseRecommendVO {
    private Long courseId;
    private String title;
    private String coverUrl;
    private Integer difficulty;
    private Integer duration;
    private List<String> tags;
    private String recommendReason;
    
    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCoverUrl() {
        return coverUrl;
    }
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
    public Integer getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public String getRecommendReason() {
        return recommendReason;
    }
    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason;
    }
    
}
