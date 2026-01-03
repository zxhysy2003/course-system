package com.sy.course_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sy.course_system.common.Result;
import com.sy.course_system.service.LearningPathService;

@RestController
@RequestMapping("/path")
public class LearningPathController {
    @Autowired
    private LearningPathService learningPathService;

    @GetMapping("/graph/{courseId}")
    public Result<?> recommendPath(@PathVariable Long courseId) {
        return Result.success(learningPathService.recommendPath(courseId));
    }
}
