package com.sy.course_system.graph.node;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Course")
public class CourseNode {
    @Id
    private Long id;

    private String name;

    public CourseNode(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public CourseNode(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
