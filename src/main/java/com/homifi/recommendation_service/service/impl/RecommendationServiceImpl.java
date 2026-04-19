package com.homifi.recommendation_service.service.impl;

import com.homifi.recommendation_service.dto.MovieRecommendationDto;
import com.homifi.recommendation_service.repository.MovieRepository;
import com.homifi.recommendation_service.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final MovieRepository movieRepository;

    public RecommendationServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String sayHello(){
        return "Greetings from the Recommendation Service!";
    }

    @Override
    public List<MovieRecommendationDto> getCollaborativeRecommendations(Long userId, Integer limit) {
        System.out.printf("Getting collaborative recommendation for userId %d", userId);
        Long startTime = System.currentTimeMillis();
        List<MovieRecommendationDto> recommendedMovies = movieRepository.getCollaborativeRecommendations(userId, limit);
        Long endTime = System.currentTimeMillis();
        System.out.printf("Time taken: %d ms", endTime - startTime);
        return recommendedMovies;
    }


    @Override
    public List<MovieRecommendationDto> getSimilarMovieRecommendations(Long movieId, Integer limit) {
        System.out.printf("Getting recommendation for movies similar to movieId %d", movieId);
        Long startTime = System.currentTimeMillis();
        List<MovieRecommendationDto> recommendedMovies = movieRepository.getSimilarMovieRecommendations(movieId, limit);
        Long endTime = System.currentTimeMillis();
        System.out.printf("Time taken: %d ms", endTime - startTime);
        return recommendedMovies;
    }

    @Override
    public List<MovieRecommendationDto> getHybridRecommendations(Long userId, Integer limit) {
        System.out.printf("Getting hybrid recommendation for userId %d", userId);
        Long startTime = System.currentTimeMillis();
        List<MovieRecommendationDto> recommendedMovies = movieRepository.getHybridRecommendations(userId, limit);
        Long endTime = System.currentTimeMillis();
        System.out.printf("Time taken: %d ms", endTime - startTime);
        return recommendedMovies;
    }

}
