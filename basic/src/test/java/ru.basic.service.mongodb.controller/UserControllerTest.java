package ru.basic.service.mongodb.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import reactor.core.publisher.Mono;
import ru.basic.service.mongodb.model.User;
import ru.basic.service.mongodb.service.UserService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebFluxTest(UserController.class)
public class UserControllerTest {

    public static final String ALICE_EXAMPLE_COM = "alice@example.com";
    public static final String ALICE = "Alice";
    public static final String ID = "1";

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserService userService;

    @Test
    void getUser_ReturnsUser_WhenFound() throws Exception {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(ID);
        Mockito.when(user.getUsername()).thenReturn("Alice");
        Mockito.when(user.getEmail()).thenReturn(ALICE_EXAMPLE_COM);

        // When
        when(userService.getUserById(ID)).thenReturn(Mono.just(user));

        // Then
        JsonPathResultMatchers jsonPathResultMatchers = jsonPath("$.username");
        webTestClient.get().uri("/api/users?id=1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(ID)
                .jsonPath("$.username").isEqualTo(ALICE)
                .jsonPath("$.email").isEqualTo(ALICE_EXAMPLE_COM);
    }

}
