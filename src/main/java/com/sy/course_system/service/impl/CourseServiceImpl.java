package com.sy.course_system.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sy.course_system.entity.Course;
import com.sy.course_system.mapper.CourseMapper;
import com.sy.course_system.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course getById(Long courseId) {
        return courseMapper.selectById(courseId);
    }

    @Override
    public List<Course> listByIds(List<Long> courseIds) {
        if (courseIds == null || courseIds.isEmpty()) {
            return Collections.emptyList();
        }
        return courseMapper.selectList(new LambdaQueryWrapper<Course>()
                .in(Course::getId, courseIds)
                .eq(Course::getStatus, 1)
            );
    }

    @Override
    public Map<Long, Course> mapByIds(List<Long> courseIds) {
        List<Course> coursesList = listByIds(courseIds);

        // 转Map，方便推荐结果组装
        return coursesList.stream()
                .collect(Collectors.toMap(
                    Course::getId, 
                    course -> course
                ));
    }
    
}
