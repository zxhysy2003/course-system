package com.sy.course_system.service;

import com.sy.course_system.behavior.enums.LearnBehaviorType;

public interface LearningBehaviorService {

    void recordBehavior(Long courseId, LearnBehaviorType behaviorType);
    
    void recordStudy(Long courseId, Integer duration);

}
