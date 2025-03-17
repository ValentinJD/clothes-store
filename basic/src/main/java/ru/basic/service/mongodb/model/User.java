package ru.basic.service.mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Indexed
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
