package com.homifi.recommendation_service.repository;

import com.homifi.recommendation_service.entity.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (u:User {userId: $userId})-[:RATED]->(m:Movie)<-[:RATED]-(other:User) " +
           "WHERE other <> u " +
           "RETURN other, count(m) as commonMovies " +
           "ORDER BY commonMovies DESC " +
           "LIMIT 10")
    List<User> findSimilarUsers(Long userId);
}
