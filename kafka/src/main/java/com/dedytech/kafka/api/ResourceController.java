package com.dedytech.kafka.api;

import com.dedytech.messaging.TicketNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/kmessage")
@RequiredArgsConstructor
public class ResourceController {

    @Value("${dedytech.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, TicketNotification> kafkaTemplate;

    public void sendMessage(@RequestBody TicketNotification ticketNotification) {
        kafkaTemplate.send(topic, UUID.randomUUID().toString(), ticketNotification);
    }

}
