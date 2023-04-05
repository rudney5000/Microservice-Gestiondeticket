package com.dedytech.ticketservice.service;

import com.dedytech.ticketservice.entity.Ticket;

public interface TicketNotificationService {

    void sendToQueue(Ticket ticket);
}
