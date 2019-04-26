package io.github.demirmustafa.samplereactiveapp.mapper.response.UserResponseMapper;

import io.github.demirmustafa.samplereactiveapp.domain.entity.User;
import io.github.demirmustafa.samplereactiveapp.model.UserResource;

public class UserResponseMapper {

    public UserResource entity2Resource(User user) {
        return UserResource.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .build();
    }
}
