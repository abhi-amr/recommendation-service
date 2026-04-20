package com.homifi.recommendation_service.controller;

import com.homifi.recommendation_service.dto.MovieRecommendationDto;
import com.homifi.recommendation_service.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/trending")
    public ResponseEntity<List<MovieRecommendationDto>> getTrendingMovies(@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(movieService.getTrendingMovies(limit));
    }

}
