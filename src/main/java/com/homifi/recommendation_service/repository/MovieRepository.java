package com.homifi.recommendation_service.repository;

import com.homifi.recommendation_service.dto.MovieRecommendationDto;
import com.homifi.recommendation_service.entity.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    /*
    * This fn returns the movies that are recommended for a user based on the movies they have rated and the ratings of other users who have rated the same movies.
    * And also sorted on rating in desc order and limit to 10 movies
    * */

    @Query("MATCH (u:User {userId: $userId})-[:RATED]->(m:Movie) " +
           "WITH u, collect(m) as movies LIMIT 10 " +
           "MATCH (m)<-[:RATED]-(other:User) WHERE m IN movies " +
           "WITH u, other, count(*) as similarity " +
           "ORDER BY similarity DESC LIMIT 100 " +
           "MATCH (other)-[r:RATED]->(rec:Movie) " +
           "WHERE NOT (u)-[:RATED]->(rec) " +
           "RETURN rec.movieId as movieId, rec.title as title, rec.genres as genres, AVG(r.rating) as averageRating " +
           "ORDER BY averageRating DESC " +
           "LIMIT $limit")
    List<MovieRecommendationDto> getCollaborativeRecommendations(Long userId, Integer limit);

    @Query("MATCH (m:Movie {movieId: $movieId})<-[r:RATED]-(u:User)-[r2:RATED]->(similar:Movie) " +
            "WHERE m <> similar " +
            "WITH similar, COUNT(u) AS noOfUsers, AVG(r2.rating) AS averageRating " +
            "RETURN similar.movieId AS movieId, " +
            "       similar.title AS title, " +
            "       similar.genres AS genres, " +
            "       averageRating " +
            "ORDER BY noOfUsers DESC " +
            "LIMIT $limit")
    List<MovieRecommendationDto> getSimilarMovieRecommendations(Long movieId, Integer limit);


    @Query("MATCH (u:User {userId: $userId})-[:RATED]->(m:Movie) " +
            "WITH u, collect(DISTINCT m) as userMovies " +
            "MATCH (m)<-[:RATED]-(other:User)-[:RATED]->(rec:Movie) " +
            "WHERE m IN userMovies AND NOT (u)-[:RATED]->(rec) " +
            "WITH DISTINCT rec, COUNT(DISTINCT other) AS collaborativeScore " +
            "OPTIONAL MATCH (rec)<-[r:RATED]-() " +
            "WITH rec, collaborativeScore, COUNT(r) AS totalRatings, AVG(r.rating) AS averageRating " +
            "RETURN rec.movieId AS movieId, " +
            "       rec.title AS title, " +
            "       rec.genres AS genres, " +
            "       averageRating, " +
            "       (0.7 * collaborativeScore + 0.3 * (totalRatings / 100.0)) AS finalScore " +
            "ORDER BY finalScore DESC " +
            "LIMIT $limit")
    List<MovieRecommendationDto> getHybridRecommendations(Long userId, Integer limit);


    /*NOT IN USE AS IT IS SLOW GIVEN CALCULATION AND FILTERING*/
    @Query("MATCH (u:User {userId: $userId})-[r1:RATED]->(m:Movie) " +
            "MATCH (m)<-[r2:RATED]-(other:User)-[r3:RATED]->(rec:Movie) " +
            "WHERE u <> other AND NOT (u)-[:RATED]->(rec) " +
            "WITH rec, other, " +
            "     SUM(r1.rating * r2.rating) AS similarityScore, " +
            "     COUNT(*) AS strength " +
            "RETURN rec.movieId AS movieId, " +
            "       rec.title AS title, " +
            "       rec.genres AS genres, " +
            "       avg(r3.rating) as averageRating " +
            "ORDER BY similarityScore DESC " +
            "LIMIT $limit")
    List<MovieRecommendationDto> getCollaborativeRecommendations2(Long userId, Integer limit);


    /*SLOW QUERY TAKING 3MINS*/
    @Query("MATCH (m:Movie)<-[r:RATED]-() " +
            "WITH m, " +
            "     COUNT(r) AS ratingCount, " +
            "     AVG(r.rating) AS averageRating " +
            "RETURN m.movieId AS movieId, " +
            "       m.title AS title, " +
            "       m.genres AS genres, " +
            "       ratingCount, " +
            "       averageRating, " +
            "       (ratingCount * 0.7 + averageRating * 0.3) AS score " +
            "ORDER BY score DESC " +
            "LIMIT $limit")
    List<MovieRecommendationDto> getTrendingMovies(Integer limit);

}
