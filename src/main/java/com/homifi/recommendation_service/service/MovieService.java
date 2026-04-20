package com.homifi.recommendation_service.service;

import com.homifi.recommendation_service.dto.MovieRecommendationDto;

import java.util.List;

public interface MovieService {
    List<MovieRecommendationDto> getTrendingMovies(Integer limit);
}
