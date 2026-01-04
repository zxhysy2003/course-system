package com.sy.course_system.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.sy.course_system.graph.UserNode;

@Repository
public interface UserNodeRepository extends Neo4jRepository<UserNode, Long> {
    @Query("""
            MERGE (u:User {id: $userId})
            """)
    void createUser(Long userId);
}
