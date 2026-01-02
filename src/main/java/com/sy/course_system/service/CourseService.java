package com.sy.course_system.service;

import java.util.List;
import java.util.Map;

import com.sy.course_system.entity.Course;

public interface CourseService {
    /**
     * 根据课程ID获取课程信息
     */
    Course getById(Long courseId);
    
    /**
     * 根据ID列表批量获取课程信息
     */
    List<Course> listByIds(List<Long> courseIds);
    
    /**
     * 根据ID列表批量获取课程，并转换为Map
     */
    Map<Long, Course> mapByIds(List<Long> courseIds);
}
