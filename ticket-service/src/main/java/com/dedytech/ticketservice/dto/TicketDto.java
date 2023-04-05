package com.dedytech.ticketservice.dto;

import com.dedytech.ticketservice.entity.PriorityType;
import com.dedytech.ticketservice.entity.TicketStatus;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private String id;

    private String description;

    private String notes;

    private String assignee;

    private Date ticketDate;

    private String priorityType;

    private String ticketStatus;
}
