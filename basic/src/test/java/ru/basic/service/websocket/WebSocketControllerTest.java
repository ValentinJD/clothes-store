package ru.basic.service.websocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
//@WebFluxTest(WebSocketController.class)
//@EnableWebSocketMessageBroker
public class WebSocketControllerTest {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private TestMessageListener testMessageListener;

    @BeforeEach
    public void setup() {
        // Очищаем полученные сообщения перед каждым тестом
        testMessageListener.clearMessages();
    }

//    @Test
    public void shouldReceiveMessageFromTopic() throws InterruptedException {
        // Отправляем сообщение через SimpMessagingTemplate
        messagingTemplate.convertAndSend("/app/hello", "World");

        // Ждем получения сообщения в слушателе
        Thread.sleep(1500); // Даем время на обработку сообщения

        assertEquals(1, testMessageListener.getReceivedMessages().size());
        assertTrue(testMessageListener.getReceivedMessages().contains("{ \"response\": \"Hello, World!\"}"));
    }

    @Component
    static class TestMessageListener {
        private List<String> messages = new ArrayList<>();

        @SubscribeMapping("/topic/greetings")
        public void handleMessage(String payload) {
            messages.add(payload);
        }

        public List<String> getReceivedMessages() {
            return messages;
        }

        public void clearMessages() {
            messages.clear();
        }
    }
}
