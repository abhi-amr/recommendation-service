package com.homifi.recommendation_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RecommendationResponse {
    private Long userId;
    private String userName;
    private Long movieId;
    private String movieTitle;
    private String movieGenres;
    private Double predictedRating;
}
