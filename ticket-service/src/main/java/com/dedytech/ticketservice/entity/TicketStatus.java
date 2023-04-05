package com.dedytech.ticketservice.entity;

import lombok.Getter;

@Getter
public enum TicketStatus {

    OPEN("Ouvrir"),
    IN_PROCESS("Il est En Cours D'Élaboration"),
    RESOLVER("Résolu"),
    CLOSED("Fermé");

    private String label;

    TicketStatus(String label) {
        this.label = label;
    }
}
