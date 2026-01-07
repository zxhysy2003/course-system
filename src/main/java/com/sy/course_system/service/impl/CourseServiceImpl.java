package com.sy.course_system.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sy.course_system.dto.CourseRegisterDTO;
import com.sy.course_system.entity.Course;
import com.sy.course_system.mapper.CourseMapper;
import com.sy.course_system.repository.CourseNodeRepository;
import com.sy.course_system.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseNodeRepository courseNodeRepository;

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

    @Override
    @Transactional
    public Integer register(CourseRegisterDTO registerDTO) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", registerDTO.getTitle());

        if (courseMapper.selectOne(queryWrapper) != null) {
            return -1; // 课程已存在
        }

        Course course = new Course();
        course.setTitle(registerDTO.getTitle());
        course.setDescription(registerDTO.getDescription());
        course.setCoverUrl(registerDTO.getCoverUrl());
        course.setDifficulty(registerDTO.getDifficulty());
        course.setDuration(registerDTO.getDuration());
        // 将标签列表转换为逗号分隔的字符串存储
        String tagsString = String.join(",", registerDTO.getTags());
        course.setTags(tagsString);
        course.setStatus(1); // 默认启用状态

        courseMapper.insert(course);

        courseNodeRepository.createCourse(course.getId(), course.getTitle());

        return 1;
    }

    @Override
    public List<Long> getKnowledgePointIdsByCourseId(Long courseId) {
        return courseMapper.selectKnowledgePointIdsByCourseId(courseId);
    }
    
}
