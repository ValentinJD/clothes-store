package ru.basic.service.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.basic.service.mongodb.model.User;
import ru.basic.service.mongodb.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/all")
    public Flux<Iterable<User>> allUsers() {
        Iterable<User> allUsers = userService.getAllUsers();
        return Flux.just(allUsers);
    }

    @GetMapping()
    public Mono<User> getById(@RequestParam("id") String id) {
        Optional<User> userById = userService.getUserById(id);
        return Mono.justOrEmpty(userById)
                .defaultIfEmpty(new User("user", "user"));
    }

    @DeleteMapping()
    public Mono<String> deleteById(@RequestParam("id") String id) {
        userService.deleteUserById(id);
        return Mono.justOrEmpty("User was deleted");
    }
}
