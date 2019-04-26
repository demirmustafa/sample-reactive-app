package io.github.demirmustafa.samplereactiveapp.config;

import io.github.demirmustafa.samplereactiveapp.domain.repository.UserRepository.UserRepository;
import io.github.demirmustafa.samplereactiveapp.mapper.request.UserRequestMapper;
import io.github.demirmustafa.samplereactiveapp.mapper.response.UserResponseMapper.UserResponseMapper;
import io.github.demirmustafa.samplereactiveapp.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository,
                                   UserResponseMapper userResponseMapper,
                                   UserRequestMapper userRequestMapper) {
        return new UserService(userRepository, userResponseMapper, userRequestMapper);
    }
}
