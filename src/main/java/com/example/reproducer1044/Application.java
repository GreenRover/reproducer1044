package com.example.reproducer1044;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class Application {

	@Autowired
	private MessagePublisher messagePublisher;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener({ApplicationReadyEvent.class})
	void willCreateAnException() {
		messagePublisher.sendMessage();
		System.out.println("You will never see this log message");
	}

}
