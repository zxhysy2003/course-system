package com.sy.course_system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.course_system.common.UserContext;
import com.sy.course_system.mapper.LearningBehaviorMapper;
import com.sy.course_system.service.LearningAnalysisService;

@Service
public class LearningAnalysisServiceImpl implements LearningAnalysisService {

    @Autowired
    private LearningBehaviorMapper learningBehaviorMapper;

    @Override
    public Integer getMyTotalStudyTime() {
        Long userId = UserContext.getUserId();
        return learningBehaviorMapper.sumStudyDurationByUser(userId);
    }

    @Override
    public List<Long> getMyLearnedCourses() {
        Long userId = UserContext.getUserId();
        return learningBehaviorMapper.selectLearnedCourseIds(userId);
    }

    @Override
    public List<Map<String, Object>> getHotCourses(Integer limit) {
        return learningBehaviorMapper.hotCourses(limit);
    }
    
}
