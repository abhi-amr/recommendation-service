package com.homifi.recommendation_service.dto;

public record MovieRecommendationDto(
    Long movieId,
    String title,
    String genres,
    Double averageRating
) {}
