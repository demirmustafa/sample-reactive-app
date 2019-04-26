package io.github.demirmustafa.samplereactiveapp.config;

import io.github.demirmustafa.samplereactiveapp.mapper.request.UserRequestMapper;
import io.github.demirmustafa.samplereactiveapp.mapper.response.UserResponseMapper.UserResponseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public UserResponseMapper userResponseMapper() {
        return new UserResponseMapper();
    }

    @Bean
    public UserRequestMapper userRequestMapper() {
        return new UserRequestMapper();
    }
}
