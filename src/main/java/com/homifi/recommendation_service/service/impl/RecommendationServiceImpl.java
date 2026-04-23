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
        System.out.println("Getting collaborative recommendation for userId %d%n".formatted(userId));
        Long startTime = System.currentTimeMillis();
        List<MovieRecommendationDto> recommendedMovies = movieRepository.getCollaborativeRecommendations(userId, limit);
        Long endTime = System.currentTimeMillis();
        System.out.println("Time taken: %d ms".formatted(endTime - startTime));
        return recommendedMovies;
    }


    @Override
    public List<MovieRecommendationDto> getSimilarMovieRecommendations(Long movieId, Integer limit) {
        System.out.println("Getting recommendation for movies similar to movieId %d".formatted(movieId));
        Long startTime = System.currentTimeMillis();
        List<MovieRecommendationDto> recommendedMovies = movieRepository.getSimilarMovieRecommendations(movieId, limit);
        Long endTime = System.currentTimeMillis();
        System.out.println("Time taken: %d ms".formatted(endTime - startTime));
        return recommendedMovies;
    }

    @Override
    public List<MovieRecommendationDto> getHybridRecommendations(Long userId, Integer limit) {
        System.out.println("Getting hybrid recommendation for userId %d".formatted(userId));
        Long startTime = System.currentTimeMillis();
        List<MovieRecommendationDto> recommendedMovies = movieRepository.getHybridRecommendations(userId, limit);
        Long endTime = System.currentTimeMillis();
        System.out.println("Time taken: %d ms".formatted(endTime - startTime));
        return recommendedMovies;
    }

}
