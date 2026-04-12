package com.homifi.recommendation_service.controller;

import com.homifi.recommendation_service.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommendationController {

    private final RecommendationService recommendationService;


    public RecommendationController(RecommendationService recommendationService){
        this.recommendationService = recommendationService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok(recommendationService.sayHello());
    }
}
