package com.homifi.recommendation_service.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.Neo4jClient;

/**
 * Neo4j configuration for creating indexes to improve query performance
 */
@Configuration
public class Neo4jConfig {

    @Bean
    public CommandLineRunner createIndexes(Neo4jClient client) {
        return args -> {
            try {
                // Create index on User.userId for faster lookups
                client.query("CREATE INDEX IF NOT EXISTS FOR (u:User) ON (u.userId)")
                        .run();

                // Create index on Movie.movieId for faster lookups
                client.query("CREATE INDEX IF NOT EXISTS FOR (m:Movie) ON (m.movieId)")
                        .run();

                // Create index on Movie.genres for genre-based searches (if needed)
                client.query("CREATE INDEX IF NOT EXISTS FOR (m:Movie) ON (m.genres)")
                        .run();

                System.out.println("✓ Neo4j indexes created successfully");
            } catch (Exception e) {
                System.err.println("⚠ Could not create indexes: " + e.getMessage());
                // This is non-critical, so continue if it fails
            }
        };
    }
}

