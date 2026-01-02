package com.sy.course_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sy.course_system.common.Result;
import com.sy.course_system.behavior.enums.LearnBehaviorType;
import com.sy.course_system.service.LearningBehaviorService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 学习行为控制器
 * 用于记录用户的学习行为，包括课程浏览、完成、学习时长和收藏等
 */
@RestController
@RequestMapping("/behavior")
public class LearningBehaviorController {
    @Autowired
    private LearningBehaviorService learningBehaviorService;

    /**
     * 记录用户查看课程的行为
     * @param courseId 课程ID
     * @return 返回操作结果
     */
    @PostMapping("/view/{courseId}")
    public Result<Integer> viewCourse(@PathVariable Long courseId) {
        learningBehaviorService.recordBehavior(courseId, LearnBehaviorType.VIEW);
        return Result.success(0);
    }
    
    /**
     * 记录用户完成课程的行为
     * @param courseId 课程ID
     * @return 返回操作结果
     */
    @PostMapping("/finish/{courseId}")
    public Result<Integer> finishCourse(@PathVariable Long courseId) {
        learningBehaviorService.recordBehavior(courseId, LearnBehaviorType.FINISH);
        return Result.success(0);
    }

    /**
     * 记录用户学习时长
     * @param courseId 课程ID
     * @param duration 学习时长（分钟）
     * @return 返回操作结果
     */
    @PostMapping("/study")
    public Result<Integer> study(@RequestParam Long courseId,
                            @RequestParam Integer duration) {
        learningBehaviorService.recordStudy(courseId, duration);
        return Result.success(0);
    }

    /**
     * 记录用户收藏课程的行为
     * @param courseId 课程ID
     * @return 返回操作结果
     */
    @PostMapping("/favourite")
    public Result<Integer> favouriteCourse(@PathVariable Long courseId) {
        learningBehaviorService.recordBehavior(courseId, LearnBehaviorType.FAVOURITE);
        return Result.success(0);
    }
}
