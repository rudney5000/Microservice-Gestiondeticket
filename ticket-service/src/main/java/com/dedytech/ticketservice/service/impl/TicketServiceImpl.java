package com.dedytech.ticketservice.service.impl;

import com.dedytech.client.AccountServiceClient;
import com.dedytech.client.contract.AccountDto;
import com.dedytech.ticketservice.dto.TicketDto;
import com.dedytech.ticketservice.entity.PriorityType;
import com.dedytech.ticketservice.entity.Ticket;
import com.dedytech.ticketservice.entity.TicketStatus;
import com.dedytech.ticketservice.entity.elastic.TicketModel;
import com.dedytech.ticketservice.repository.TicketRepository;
import com.dedytech.ticketservice.repository.elastic.TicketElasticRepository;
import com.dedytech.ticketservice.service.TicketNotificationService;
import com.dedytech.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketElasticRepository ticketElasticRepository;
    private final TicketRepository ticketRepository;
    private final TicketNotificationService TicketNotificationService;
    private final AccountServiceClient accountServiceClient;

    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        // Ticket Entity
        Ticket ticket = new Ticket();
        ResponseEntity<AccountDto> accountDtoResponseEntity = accountServiceClient.get(ticketDto.getAssignee());


        if (ticketDto.getDescription() == null)
            throw  new IllegalArgumentException("Description cannot be null");
        ticket.setDescription(ticketDto.getDescription());
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));
        ticket.setAssignee(accountDtoResponseEntity.getBody().getId());

        // save in mysql
        ticket = ticketRepository.save(ticket);

        // create TicketModel
        TicketModel ticketModel = TicketModel.builder()
                .description(ticket.getDescription())
                .notes(ticket.getNotes())
                .id(ticket.getId())
                .assignee(accountDtoResponseEntity.getBody().getNameSurname())
                .priorityType(ticket.getPriorityType().getLabel())
                .ticketStatus(ticket.getTicketStatus().getLabel())
                .ticketDate(ticket.getTicketDate()).build();

        // save in Elastic
        ticketElasticRepository.save(ticketModel);

        // objet forme
        ticketDto.setId(ticket.getId());

        // queue of notification
        TicketNotificationService.sendToQueue(ticket);
        return ticketDto;
    }

    @Override
    public TicketDto update(String id, TicketDto ticketDto) {
        return null;
    }

    @Override
    public TicketDto getById(String ticketId) {
        return null;
    }

    @Override
    public Page<TicketDto> getPagination(Pageable pageable) {
        return null;
    }
}
