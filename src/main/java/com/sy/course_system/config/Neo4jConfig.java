package com.sy.course_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
@Configuration
@EnableNeo4jRepositories(basePackages = "com.sy.course_system.repository")
public class Neo4jConfig {
    @Bean
    public Driver neo4jDriver() {
        return GraphDatabase.driver(
                "bolt://localhost:7687",
                AuthTokens.basic("neo4j", "neo4j123"));
    } 
}
