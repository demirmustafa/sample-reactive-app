package io.github.demirmustafa.samplereactiveapp.mapper.request;

import io.github.demirmustafa.samplereactiveapp.domain.entity.User;
import io.github.demirmustafa.samplereactiveapp.model.request.CreateUserRequest;

public class UserRequestMapper {

    public User createRequest2Entity(CreateUserRequest request) {
        return User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .build();
    }
}
