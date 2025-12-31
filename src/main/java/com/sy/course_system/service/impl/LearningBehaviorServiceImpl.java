package com.sy.course_system.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.course_system.common.UserContext;
import com.sy.course_system.entity.LearningBehavior;
import com.sy.course_system.mapper.LearningBehaviorMapper;
import com.sy.course_system.service.LearningBehaviorService;

@Service
public class LearningBehaviorServiceImpl implements LearningBehaviorService {
    @Autowired
    private LearningBehaviorMapper learningBehaviorMapper;

    @Override
    public void recordBehavior(Long courseId, String behaviorType) {
        
        LearningBehavior behavior = new LearningBehavior();
        behavior.setUserId(UserContext.getUserId());
        behavior.setCourseId(courseId);
        behavior.setBehaviorType(behaviorType);
        behavior.setDuration(0);
        behavior.setCreateTime(LocalDateTime.now());

        learningBehaviorMapper.insert(behavior);
    }

    @Override
    public void recordStudy(Long courseId, Integer duration) {
        
        LearningBehavior behavior = new LearningBehavior();
        behavior.setUserId(UserContext.getUserId());
        behavior.setCourseId(courseId);
        behavior.setBehaviorType("STUDY");
        behavior.setDuration(duration);
        behavior.setCreateTime(LocalDateTime.now());

        learningBehaviorMapper.insert(behavior);
    }
}
