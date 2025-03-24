package ru.basic.service.mongodb.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.basic.service.mongodb.model.User;


public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
