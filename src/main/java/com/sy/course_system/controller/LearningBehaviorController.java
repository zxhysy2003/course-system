package com.sy.course_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sy.course_system.common.Result;
import com.sy.course_system.service.LearningBehaviorService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/behavior")
public class LearningBehaviorController {
    @Autowired
    private LearningBehaviorService learningBehaviorService;

    @PostMapping("/view/{courseId}")
    public Result<Integer> viewCourse(@PathVariable Long courseId) {
        learningBehaviorService.recordBehavior(courseId, "VIEW");
        return Result.success(0);
    }
    
    @PostMapping("/finish/{courseId}")
    public Result<Integer> finishCourse(@PathVariable Long courseId) {
        learningBehaviorService.recordBehavior(courseId, "FINISH");
        return Result.success(0);
    }

    @PostMapping("/study")
    public Result<Integer> study(@RequestParam Long courseId,
                            @RequestParam Integer duration) {
        learningBehaviorService.recordStudy(courseId, duration);
        return Result.success(0);
    }
}
