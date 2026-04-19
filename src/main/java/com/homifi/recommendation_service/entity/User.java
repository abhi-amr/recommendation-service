package com.homifi.recommendation_service.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Getter
@Setter
@Node("User")
public class User {

    @Id
    private Long userId;

    @Relationship(type = "RATED", direction = Relationship.Direction.OUTGOING)
    private Set<Rating> ratings;

    @Relationship(type = "TAGGED", direction = Relationship.Direction.OUTGOING)
    private Set<Tagged> tags;

    public User() {}

    public User(Long userId) {
        this.userId = userId;
    }

}
