package com.example.reproducer1044;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    @Autowired
    private StreamBridge streamBridge;

    public void sendMessage() {
        Message<String> message = MessageBuilder
                .withPayload("Just a demo")
                .build();
        streamBridge.send("demoBinding-out-0", message);
    }
}
