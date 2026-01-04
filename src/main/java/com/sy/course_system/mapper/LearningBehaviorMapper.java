package com.sy.course_system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sy.course_system.behavior.entity.LearningBehavior;
import com.sy.course_system.dto.UserCourseBaseScoreDTO;
import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface LearningBehaviorMapper extends BaseMapper<LearningBehavior> {

    // 用户总学习时长
    @Select("""
            SELECT IFNULL(SUM(duration), 0)
            FROM learning_behavior
            WHERE user_id = #{userId}
            AND behavior_type = 'STUDY'
            """)
    Integer sumStudyDurationByUser(@Param("userId") Long userId);

    // 用户学习过的课程列表
    @Select("""
            SELECT DISTINCT course_id
            FROM learning_behavior
            WHERE user_id = #{userId}
            """)
    List<Long> selectLearnedCourseIds(@Param("userId") Long userId);
    
    // 热门课程排行
    @Select("""
            SELECT course_id, COUNT(*) AS study_count
            FROM learning_behavior
            GROUP BY course_id
            ORDER BY study_count DESC
            LIMIT #{limit}
            """)
    List<Map<String, Object>> hotCourses(@Param("limit") Integer limit);

    @Select("""
                SELECT 
                        user_id,
                        course_id,
                        SUM(LOG(1 + IFNULL(lb.duration, 0)) * bw.weight) AS base_score,
                        MAX(lb.create_time) AS last_time
                FROM learning_behavior lb
                JOIN behavior_weight bw
                  ON lb.behavior_type = bw.behavior_type
                GROUP BY user_id, course_id;
            """)
    List<UserCourseBaseScoreDTO> listUserCourseBaseScores();

}
