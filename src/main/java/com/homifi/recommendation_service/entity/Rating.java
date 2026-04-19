package com.homifi.recommendation_service.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Getter
@Setter
@RelationshipProperties
public class Rating {

    @RelationshipId
    private Long id;

    private Double rating;

    private Long timestamp;

    @TargetNode
    private Movie movie;

    public Rating() {}

    public Rating(Double rating, Long timestamp, Movie movie) {
        this.rating = rating;
        this.timestamp = timestamp;
        this.movie = movie;
    }


}
