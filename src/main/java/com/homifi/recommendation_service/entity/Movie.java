package com.homifi.recommendation_service.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Getter
@Setter
@Node("Movie")
public class Movie {

    @Id
    private Long movieId;

    private String title;

    private String genres;

    @Relationship(type = "RATED", direction = Relationship.Direction.INCOMING)
    private Set<Rating> ratings;

    @Relationship(type = "TAGGED", direction = Relationship.Direction.INCOMING)
    private Set<Tagged> tags;

    public Movie() {}

    public Movie(Long movieId, String title, String genres) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
    }
}
