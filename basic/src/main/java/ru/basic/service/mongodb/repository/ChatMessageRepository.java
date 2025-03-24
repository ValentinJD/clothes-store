package ru.basic.service.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.basic.service.mongodb.model.ChatMessage;
import ru.basic.service.mongodb.model.MessageStatus;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    long countBySenderIdAndRecipientIdAndStatus(String senderId,
                                                String recipientId,
                                                MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

}