package com.homifi.recommendation_service.controller;

import com.homifi.recommendation_service.dto.MovieRecommendationDto;
import com.homifi.recommendation_service.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;


    public RecommendationController(RecommendationService recommendationService){
        this.recommendationService = recommendationService;
    }


    /*
    * Say Hello to the World
    * */
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok(recommendationService.sayHello());
    }

    /*s
    * Find users with similar taste
    * Recommend movies they liked but current user hasn’t seen
    *
    * User-Based Collaborative Filtering
    * */
    @GetMapping("/collaborative/{userId}")
    public ResponseEntity<List<MovieRecommendationDto>> getCollaborativeRecommendations(@PathVariable Long userId,
                                                                                        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<MovieRecommendationDto> recommendations = recommendationService.getCollaborativeRecommendations(userId, limit);
        return ResponseEntity.ok(recommendations);
    }


    /*
    * Find movies watched by same users
    * Rank by co-occurrence
    *
    * Item-Based Recommendations
    * */
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<MovieRecommendationDto>> getSimilarRecommendations(@PathVariable Long movieId,
                                                                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<MovieRecommendationDto> recommendations = recommendationService.getSimilarMovieRecommendations(movieId, limit);
        return ResponseEntity.ok(recommendations);
    }


    /*
     * Combine:
     *  Collaborative score
     *  Popularity score
     *
     * Hybrid Recommendations
     * */
    @GetMapping("/hybrid/{userId}")
    public ResponseEntity<List<MovieRecommendationDto>> getHybridRecommendations(@PathVariable Long userId,
                                                                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<MovieRecommendationDto> recommendations = recommendationService.getHybridRecommendations(userId, limit);
        return ResponseEntity.ok(recommendations);
    }


}
