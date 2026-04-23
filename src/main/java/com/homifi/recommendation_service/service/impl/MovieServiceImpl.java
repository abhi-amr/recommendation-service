package com.homifi.recommendation_service.service.impl;

import com.homifi.recommendation_service.dto.MovieRecommendationDto;
import com.homifi.recommendation_service.repository.MovieRepository;
import com.homifi.recommendation_service.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieRecommendationDto> getTrendingMovies(Integer limit){
        System.out.println("Getting trending movies");
        Long startTime = System.currentTimeMillis();
        List<MovieRecommendationDto> trendingMovies = movieRepository.getTrendingMovies(limit);
        Long endTime = System.currentTimeMillis();
        System.out.println("Time taken: %d ms".formatted(endTime - startTime));
        return trendingMovies;
    }

}
