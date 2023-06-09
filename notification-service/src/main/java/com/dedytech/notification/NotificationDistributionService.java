package com.dedytech.notification;

import com.dedytech.messaging.TicketNotification;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class NotificationDistributionService {

    @StreamListener(Sink.INPUT)
    public void onNotification(TicketNotification ticketNotification) {
        System.out.println("=====================================================================");

        System.out.println("Notification je n'arrive pas à l'envoyer aux utilisateurs lorsqu'il est reçu");
        System.out.println("Notification -> " + ticketNotification.toString());

    }
}
