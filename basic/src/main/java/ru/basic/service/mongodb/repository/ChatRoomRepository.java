package ru.basic.service.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.basic.service.mongodb.model.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
