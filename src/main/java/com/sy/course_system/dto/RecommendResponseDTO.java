package com.sy.course_system.dto;

import java.util.List;

public class RecommendResponseDTO {
    private Long userId;
    private List<Long> courseIds;
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public List<Long> getCourseIds() {
        return courseIds;
    }
    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }

    
}
