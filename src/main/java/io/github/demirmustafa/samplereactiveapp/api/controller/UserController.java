package io.github.demirmustafa.samplereactiveapp.api.controller;

import io.github.demirmustafa.samplereactiveapp.api.ReactiveApi;
import io.github.demirmustafa.samplereactiveapp.model.UserResource;
import io.github.demirmustafa.samplereactiveapp.model.request.CreateUserRequest;
import io.github.demirmustafa.samplereactiveapp.model.request.UpdateUserRequest;
import io.github.demirmustafa.samplereactiveapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveApi
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public Flux<UserResource> getAll() {
        return userService.findAll();
    }

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserResource> create(@RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @PutMapping("/api/users/{id}")
    public Mono<UserResource> update(@PathVariable String id, @RequestBody UpdateUserRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return userService.delete(id);
    }
}