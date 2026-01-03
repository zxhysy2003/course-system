package com.sy.course_system.service.impl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.course_system.dto.LearningPathDto;
import com.sy.course_system.repository.LearningPathRepository;
import com.sy.course_system.service.LearningPathService;
import com.sy.course_system.vo.LearningPathRecommendVO;

@Service
public class LearningPathServiceImpl implements LearningPathService {

    @Autowired
    private LearningPathRepository learningPathRepository;


    @Override
    public List<LearningPathRecommendVO> recommendPath(Long courseId) {
        
        List<LearningPathDto> nodes = learningPathRepository.recommendPath(courseId, 5);

        if (nodes == null || nodes.isEmpty()) {
            return Collections.emptyList();
        }

        return nodes.stream().map(dto -> {
            LearningPathRecommendVO vo = new LearningPathRecommendVO();
            vo.setFromId(dto.getFromId());
            vo.setFromName(dto.getFromName());
            vo.setToId(dto.getToId());
            vo.setToName(dto.getToName());
            vo.setSupport(dto.getSupport());
            vo.buildReason();
            return vo;
        }).toList();

    }

}
