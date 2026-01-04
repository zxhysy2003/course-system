package com.sy.course_system.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.course_system.common.UserContext;
import com.sy.course_system.common.util.TimeDecayUtil;
import com.sy.course_system.dto.UserCourseBaseScoreDTO;
import com.sy.course_system.dto.UserCourseScoreDTO;
import com.sy.course_system.behavior.enums.LearnBehaviorType;
import com.sy.course_system.behavior.entity.LearningBehavior;
import com.sy.course_system.mapper.LearningBehaviorMapper;
import com.sy.course_system.service.LearningBehaviorService;

@Service
public class LearningBehaviorServiceImpl implements LearningBehaviorService {
    @Autowired
    private LearningBehaviorMapper learningBehaviorMapper;

    @Override
    public void recordBehavior(Long courseId, LearnBehaviorType behaviorType) {
        
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
        behavior.setBehaviorType(LearnBehaviorType.STUDY);
        behavior.setDuration(duration);
        behavior.setCreateTime(LocalDateTime.now());

        learningBehaviorMapper.insert(behavior);
    }

    @Override
    public List<LearningBehavior> listAllBehaviors() {
        return learningBehaviorMapper.selectList(null);
    }

    @Override
    public List<UserCourseScoreDTO> listAggregatedScores() {
        List<UserCourseBaseScoreDTO> baseScores = learningBehaviorMapper.listUserCourseBaseScores();

        return baseScores.stream().map(bs -> {
            UserCourseScoreDTO dto = new UserCourseScoreDTO();
            dto.setUserId(bs.getUserId());
            dto.setCourseId(bs.getCourseId());
            dto.setScore(bs.getBaseScore() * TimeDecayUtil.decay(bs.getLastTime()));
            return dto;
        }).toList();
    }
}
