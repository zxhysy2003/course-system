package com.sy.course_system.service;

import java.util.List;
import java.util.Map;

public interface LearningAnalysisService {
    
    Integer getMyTotalStudyTime();

    List<Long> getMyLearnedCourses();

    List<Map<String, Object>> getHotCourses(Integer limit);
}
