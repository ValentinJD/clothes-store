package ru.basic.service.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.basic.service.mongodb.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}
