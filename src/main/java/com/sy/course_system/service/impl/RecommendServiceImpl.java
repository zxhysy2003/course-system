package com.sy.course_system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sy.course_system.behavior.entity.LearningBehavior;
import com.sy.course_system.behavior.service.ScoreAggregateService;
import com.sy.course_system.dto.RecommendRequestDTO;
import com.sy.course_system.dto.RecommendResponseDTO;
import com.sy.course_system.dto.UserCourseScoreDTO;
import com.sy.course_system.service.LearningBehaviorService;
import com.sy.course_system.service.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LearningBehaviorService learningBehaviorService;

    @Value("${recommend.service.url}")
    private String recommendServiceUrl;

    public RecommendResponseDTO recommend(Long userId) {

        // 1. 获取所有用户的学习行为数据
        List<LearningBehavior> behaviors = learningBehaviorService.listAllBehaviors();

        // 2. 聚合成 (userId, courseId, score) 格式的数据
        Map<String, Double> scoresMap = ScoreAggregateService.aggregate(behaviors);

        List<UserCourseScoreDTO> scoreList = ScoreAggregateService.toScoreDTO(scoresMap);

        // 3. 调用推荐服务
        RecommendRequestDTO request = new RecommendRequestDTO();
        request.setTargetUserId(userId);
        request.setData(scoreList);
        request.setTopN(5); // 假设推荐前5个课程

        return restTemplate.postForObject(
            recommendServiceUrl + "/recommend",
            request, 
            RecommendResponseDTO.class
        );
    }
}
