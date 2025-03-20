package ru.basic.service.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRate = 10000)
    private void sendChange() {
        log.info("Sending message");
        simpMessagingTemplate.convertAndSend("/topic/greetings", "{ \"response\": \"Hello, " + "UPDATE" + "!\"}");
    }


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Mono<String> greeting(SimpMessageHeaderAccessor headers, String body) throws Exception {
        // Получаем команду
        String command = headers.getMessageType().toString();

        // Получаем заголовки
        MessageHeaders messageHeaders = headers.getMessageHeaders();
        for (Map.Entry<String, Object> entry : messageHeaders.entrySet()) {
            System.out.println("Header Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // Получаем тело сообщения
        System.out.println("Body: " + body);
        // Обработка сообщения, можно добавить задержку для имитации обработки
        Thread.sleep(1000);
        return Mono.just("{ \"response\": \"Hello, " + body + "!\"}");
    }


}
