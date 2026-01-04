package com.sy.course_system.service;

import java.util.List;

import com.sy.course_system.behavior.entity.LearningBehavior;
import com.sy.course_system.behavior.enums.LearnBehaviorType;
import com.sy.course_system.dto.UserCourseScoreDTO;

public interface LearningBehaviorService {

    void recordBehavior(Long courseId, LearnBehaviorType behaviorType);
    
    void recordStudy(Long courseId, Integer duration);

    List<LearningBehavior> listAllBehaviors();

    List<UserCourseScoreDTO> listAggregatedScores();
}
