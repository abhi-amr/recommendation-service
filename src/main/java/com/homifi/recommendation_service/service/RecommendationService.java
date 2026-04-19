package com.homifi.recommendation_service.service;

import com.homifi.recommendation_service.dto.MovieRecommendationDto;

import java.net.Inet4Address;
import java.util.List;

public interface RecommendationService {
    String sayHello();;

    /*
    * User based collaborative filtering recommendation
    * */
    List<MovieRecommendationDto> getCollaborativeRecommendations(Long userId, Integer limit);



    /*
    * Item based recommendations
    * */
    List<MovieRecommendationDto> getSimilarMovieRecommendations(Long movieId, Integer limit);


    /*
    * Hybrid based recommendations
    * */
    List<MovieRecommendationDto> getHybridRecommendations(Long userId, Integer limit);

}
