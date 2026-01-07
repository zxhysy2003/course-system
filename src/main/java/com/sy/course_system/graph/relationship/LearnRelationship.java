package com.sy.course_system.graph.relationship;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.sy.course_system.graph.node.CourseNode;

@RelationshipProperties
public class LearnRelationship {
    // Neo4j 内部 ID
    @Id
    @GeneratedValue
    private Long neo4jId;

    // 业务 ID
    private String id;

    private String type; // VIEW / STUDY / FINISH / FAVOURITE

    private Integer duration; // in minutes

    private LocalDateTime time; // start time

    @TargetNode
    private CourseNode course;

    public LearnRelationship(String type, Integer duration, CourseNode course) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.duration = duration;
        this.course = course;
        this.time = LocalDateTime.now();
    }

    public LearnRelationship() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public CourseNode getCourse() {
        return course;
    }

    public void setCourse(CourseNode course) {
        this.course = course;
    }

}
