package com.sy.course_system.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("learning_behavior")
public class LearningBehavior {
    private Long id;
    private Long userId;
    private Long courseId;
    private String behaviorType;
    private Integer duration;
    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public String getBehaviorType() {
        return behaviorType;
    }
    public void setBehaviorType(String behaviorType) {
        this.behaviorType = behaviorType;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    
}
