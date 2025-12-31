package com.sy.course_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sy.course_system.common.Result;
import com.sy.course_system.common.UserContext;
import com.sy.course_system.dto.RecommendResponseDTO;
import com.sy.course_system.service.impl.RecommendServiceImpl;

@RestController
@RequestMapping("/recommend")
public class RecommendController {
    @Autowired
    private RecommendServiceImpl recommendService;

    @GetMapping("/courses")
    public Result<RecommendResponseDTO> recommendCourses() {
        
        Long userId = UserContext.getUserId();

        return Result.success(recommendService.recommend(userId));

    }
}
