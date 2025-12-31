package com.sy.course_system.service;

public interface LearningBehaviorService {

    void recordBehavior(Long courseId, String behaviorType);
    
    void recordStudy(Long courseId, Integer duration);

}
