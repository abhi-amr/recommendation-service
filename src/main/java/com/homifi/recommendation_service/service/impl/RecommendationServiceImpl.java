package com.homifi.recommendation_service.service.impl;

import com.homifi.recommendation_service.service.RecommendationService;
import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    public String sayHello(){
        return "Greetings from the Recommendation Service!";
    }
}
