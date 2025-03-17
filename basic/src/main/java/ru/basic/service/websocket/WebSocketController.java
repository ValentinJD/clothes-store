package ru.basic.service.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {
        // Обработка сообщения, можно добавить задержку для имитации обработки
        Thread.sleep(1000);
        return "{ \"response\": \"Hello, " + message + "!\"}";
    }
}
