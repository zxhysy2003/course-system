package com.sy.course_system.graph;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("User")
public class UserNode {
    @Id
    private Long id;

    public UserNode(Long id) {
        this.id = id;
    }

    public UserNode(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
