package com.sy.course_system.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.sy.course_system.graph.node.KnowledgePointNode;

public interface KnowledgePointRepository extends Neo4jRepository<KnowledgePointNode, Long> {

    // 保存或更新知识点节点
    @Query("""
            MERGE (kp:KnowledgePoint {id: $id})
            ON CREATE SET
                kp.name = $name,
                kp.difficulty = $difficulty
            ON MATCH SET
                kp.name = $name,
                kp.difficulty = $difficulty
            """)
    void saveOrUpdateKnowledgePoint(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("difficulty") Integer difficulty);

    // 创建先修关系
    @Query("""
            MATCH (pre:KnowledgePoint {id: $preId})
            MATCH (next:KnowledgePoint {id: $nextId})
            MERGE (pre)-[:PREREQUISITE]->(next)
            """)
    void createPrerequisiteRelation(
            @Param("preId") Long prerequisiteId,
            @Param("nextId") Long nextId);

    // 标记用户掌握某个知识点
    // 触发时机: 完成课程/测验通过/学习时长达到阈值
    @Query("""
            MATCH (u:User {id: $userId})
            MATCH (kp:KnowledgePoint {id: $kpId})
            MERGE (u)-[:MASTERED]->(kp)
            """)
    void markUserMasteredKnowledgePoint(
            @Param("userId") Long userId,
            @Param("kpId") Long kpId);

    // 查询用户未掌握的先修知识点
    @Query("""
            MATCH (u:User {id: $userId}),
                  (kp:KnowledgePoint {id: $kpId})
            MATCH (pre:KnowledgePoint)-[:PREREQUISITE]->(kp)
            WHERE NOT (u)-[:MASTERED]->(pre)
            RETURN pre
            """)
    List<KnowledgePointNode> findUnmasteredPrerequisites(
            @Param("userId") Long userId,
            @Param("kpId") Long currentKpId);

    // 查询用户未掌握的后续知识点
    // 触发时机: 用户掌握当前知识点后，推荐后续知识点
    @Query("""
            MATCH (u:User {id: $userId}),
                  (kp:KnowledgePoint {id: $kpId})
            MATCH (kp)-[:PREREQUISITE]->(next:KnowledgePoint)
            WHERE NOT (u)-[:MASTERED]->(next)
            RETURN next
            """)
    List<KnowledgePointNode> findUnmasteredNextKnowledgePoints(
            @Param("userId") Long userId,
            @Param("kpId") Long currentKpId);

    // 根据难度推荐后续知识点
    @Query("""
            MATCH (u:User {id: $userId}),
                  (kp:KnowledgePoint {id: $kpId})
            MATCH (kp)-[:PREREQUISITE]->(next:KnowledgePoint)
            WHERE next.difficulty <= kp.difficulty + 1
              AND NOT (u)-[:MASTERED]->(next)
            RETURN next
            ORDER BY next.difficulty ASC
            LIMIT $limit
            """)
    List<KnowledgePointNode> recommendNextByDifficulty(
            @Param("userId") Long userId,
            @Param("kpId") Long currentKpId,
            @Param("limit") int limit);

    // 推荐学习路径（多步先修关系）
    @Query("""
            MATCH (u:User {id: $userId}),
                  (start:KnowledgePoint {id: $kpId})

            MATCH p = (start)-[:PREREQUISITE*1..3]->(next:KnowledgePoint)

            WHERE
              ALL(n IN nodes(p)[1..] WHERE NOT (u)-[:MASTERED]->(n))
              AND next.difficulty <= start.difficulty + 1

            RETURN nodes(p) AS path
            ORDER BY length(p), next.difficulty
            LIMIT 5
            """)
    List<List<KnowledgePointNode>> recommendLearningPaths(
            @Param("userId") Long userId,
            @Param("kpId") Long currentKpId);
}
