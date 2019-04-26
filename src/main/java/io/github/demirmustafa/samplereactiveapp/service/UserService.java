package io.github.demirmustafa.samplereactiveapp.service;

import io.github.demirmustafa.samplereactiveapp.domain.entity.User;
import io.github.demirmustafa.samplereactiveapp.domain.repository.UserRepository.UserRepository;
import io.github.demirmustafa.samplereactiveapp.mapper.request.UserRequestMapper;
import io.github.demirmustafa.samplereactiveapp.mapper.response.UserResponseMapper.UserResponseMapper;
import io.github.demirmustafa.samplereactiveapp.model.UserResource;
import io.github.demirmustafa.samplereactiveapp.model.request.CreateUserRequest;
import io.github.demirmustafa.samplereactiveapp.model.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;

    public Flux<UserResource> findAll() {
        return userRepository.findAll()
                .map(userResponseMapper::entity2Resource);
    }

    public Mono<UserResource> create(CreateUserRequest request) {
        User user = userRequestMapper.createRequest2Entity(request);
        return userRepository.save(user)
                .map(userResponseMapper::entity2Resource);
    }

    public Mono<UserResource> update(String id, UpdateUserRequest request) {
        return Mono.just(id)
                .flatMap(userRepository::findById)
                .switchIfEmpty(Mono.error(new RuntimeException("No record found")))
                .map(existingUser -> {
                    existingUser.setName(request.getName());
                    existingUser.setSurname(request.getSurname());
                    existingUser.setUsername(request.getUsername());
                    return existingUser;
                }).flatMap(userRepository::save)
                .map(userResponseMapper::entity2Resource);
    }

    public Mono<Void> delete(String id) {
        return Mono.just(id)
                .flatMap(userRepository::findById)
                .switchIfEmpty(Mono.error(new RuntimeException("No record found")))
                .flatMap(userRepository::delete);
    }
}
