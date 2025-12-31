package com.sy.course_system.behavior.service;

import com.sy.course_system.behavior.entity.LearningBehavior;
import com.sy.course_system.behavior.model.ImplicitScoreCalculator;
import com.sy.course_system.dto.UserCourseScoreDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ScoreAggregateService {
    public static Map<String, Double> aggregate(List<LearningBehavior> behaviors) {
        return behaviors.stream()
                .collect(Collectors.groupingBy(
                        b -> b.getUserId() + "_" + b.getCourseId(),
                        Collectors.summingDouble(ImplicitScoreCalculator::calculate)
                ));
    }
    public static List<UserCourseScoreDTO> toScoreDTO(Map<String, Double> scoreMap) {
        List<UserCourseScoreDTO> list = new ArrayList<>();
        for (Map.Entry<String, Double> entry : scoreMap.entrySet()) {
            String[] parts = entry.getKey().split("_");
            UserCourseScoreDTO dto = new UserCourseScoreDTO();
            dto.setUserId(Long.valueOf(parts[0]));
            dto.setCourseId(Long.valueOf(parts[1]));
            dto.setScore(entry.getValue());
            list.add(dto);
        }
        return list;
    }
}
