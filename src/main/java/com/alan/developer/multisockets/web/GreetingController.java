package com.alan.developer.multisockets.web;

import com.alan.developer.multisockets.model.Greeting;
import com.alan.developer.multisockets.model.HelloMessage;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Log
@Controller
public class GreetingController {

    private SimpMessagingTemplate simpMessagingTemplate;

    GreetingController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/chat/{room}")
    @SendTo("/chat/{room}")
    public Greeting chat(HelloMessage message) throws Exception {
        log.info("entrando mensaje " + message);
        return new Greeting(HtmlUtils.htmlEscape(message.getName()));
    }
}
