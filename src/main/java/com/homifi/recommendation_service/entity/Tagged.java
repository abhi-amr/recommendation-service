package com.homifi.recommendation_service.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Getter
@Setter
@RelationshipProperties
public class Tagged {

    @RelationshipId
    private Long id;

    private String tag;

    private Long timestamp;

    @TargetNode
    private Movie movie;

    public Tagged() {}

    public Tagged(String tag, Long timestamp, Movie movie) {
        this.tag = tag;
        this.timestamp = timestamp;
        this.movie = movie;
    }
}
