package io.github.demirmustafa.samplereactiveapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = {"io.github.demirmustafa.samplereactiveapp.domain.repository"})
public class MongoConfiguration {
}
