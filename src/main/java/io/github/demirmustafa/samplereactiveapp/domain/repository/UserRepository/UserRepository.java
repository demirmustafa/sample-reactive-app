package io.github.demirmustafa.samplereactiveapp.domain.repository.UserRepository;

import io.github.demirmustafa.samplereactiveapp.domain.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
}
