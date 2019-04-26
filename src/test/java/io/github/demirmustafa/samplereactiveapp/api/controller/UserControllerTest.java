package io.github.demirmustafa.samplereactiveapp.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.demirmustafa.samplereactiveapp.domain.entity.User;
import io.github.demirmustafa.samplereactiveapp.model.request.CreateUserRequest;
import io.github.demirmustafa.samplereactiveapp.model.request.UpdateUserRequest;
import io.github.demirmustafa.samplereactiveapp.service.UserService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@WebFluxTest(value = UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private WebTestClient webTestClient;

    /**
     * {@link UserController#getAll()}
     */

    @Test
    public void should_get_all_users() {
        //given
        doReturn(
                Flux.just(
                        User.builder()
                                .id("123")
                                .name("Mustafa")
                                .surname("Demir")
                                .username("javalog")
                                .build()
                )
        ).when(userService).findAll();

        //when
        webTestClient
                .get()
                .uri("/api/users")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("@.[0].id").exists()
                .jsonPath("@.[0].name").isEqualTo("Mustafa")
                .jsonPath("@.[0].surname").isEqualTo("Demir")
                .jsonPath("@.[0].username").isEqualTo("javalog");
    }

    /**
     * {@link UserController#create(CreateUserRequest)}
     */

    @Test
    @SneakyThrows
    public void should_create_user() {
        //given
        CreateUserRequest request = new ObjectMapper().readValue(new ClassPathResource("data/CREATE_USER_REQUEST.json").getFile(), CreateUserRequest.class);
        doReturn(
                Mono.just(
                        User.builder()
                                .id("123")
                                .name("Mustafa")
                                .surname("Demir")
                                .username("javalog")
                                .build()
                )
        ).when(userService).create(any(CreateUserRequest.class));

        //when
        WebTestClient.ResponseSpec responseSpec = webTestClient
                .post()
                .uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CreateUserRequest.class)
                .exchange();

        //then
        responseSpec
                .expectStatus()
                .isCreated()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("@.id").exists()
                .jsonPath("@.name").isEqualTo("Mustafa")
                .jsonPath("@.surname").isEqualTo("Demir")
                .jsonPath("@.username").isEqualTo("javalog");
    }

    /**
     * {@link UserController#update(String, UpdateUserRequest)}
     */

    @Test
    @SneakyThrows
    public void should_update_user() {
        //given
        UpdateUserRequest request = new ObjectMapper().readValue(new ClassPathResource("data/UPDATE_USER_REQUEST.json").getFile(), UpdateUserRequest.class);
        doReturn(
                Mono.just(
                        User.builder()
                                .id("123")
                                .name("Mustafa")
                                .surname("Demir")
                                .username("updatedUsername")
                                .build()
                )
        ).when(userService).update(anyString(), any(UpdateUserRequest.class));

        //when
        WebTestClient.ResponseSpec responseSpec = webTestClient
                .put()
                .uri("/api/users/{id}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), UpdateUserRequest.class)
                .exchange();

        //then
        responseSpec
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("@.id").exists()
                .jsonPath("@.name").isEqualTo("Mustafa")
                .jsonPath("@.surname").isEqualTo("Demir")
                .jsonPath("@.username").isEqualTo("updatedUsername");
    }

    /**
     * {@link UserController#delete(String)}
     */

    @Test
    public void should_delete_user() {
        //given
        doReturn(
                Mono.empty()
        ).when(userService).delete(anyString());

        //when
        WebTestClient.ResponseSpec responseSpec = webTestClient
                .delete()
                .uri("/api/users/{id}", 123)
                .exchange();

        //then
        responseSpec
                .expectStatus()
                .isNoContent();
    }


}
