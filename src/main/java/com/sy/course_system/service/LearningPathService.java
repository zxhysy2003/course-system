package com.sy.course_system.service;

import java.util.List;

import com.sy.course_system.graph.node.KnowledgePointNode;
import com.sy.course_system.vo.LearningPathRecommendVO;

public interface LearningPathService {
    List<LearningPathRecommendVO> recommendPath(Long courseId);

    List<List<KnowledgePointNode>> recommendLearningPathsForUser(Long kpId);
}
