package com.dedytech.kafka.service;

import com.dedytech.messaging.TicketNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaListenerService {

    @KafkaListener(
            topics = "${dedytech.kafka.topic}",
            groupId = "${dedytech.kafka.group.id}"
    )
    public void listen(@Payload TicketNotification ticketNotification) {
        log.info("Message received.. MessageID : {} Message: {} Date : {}",
                ticketNotification.getTicketId(),
                ticketNotification.getAccountId(),
                ticketNotification.getTicketDescription());
    }
}
