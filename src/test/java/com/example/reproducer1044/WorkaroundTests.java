package com.example.reproducer1044;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("crash")
@Import(TestChannelBinderConfiguration.class)
class WorkaroundTests {

    @Autowired
    OutputDestination outputDestination;

    @Autowired
    MessagePublisher messagePublisher;

    @Test
    void messagePublisher_shouldAddHeader_fromConfig() {

        messagePublisher.sendMessage();
        Message<byte[]> result = outputDestination.receive(100, "target-topic");

        assertThat(result.getHeaders().get("senderId"), is("myApplicationName"));
        assertThat(result.getHeaders().get("timeToLive"), is(23000L));
    }

}