package com.dedytech.ticketservice.entity;

import lombok.Getter;

@Getter
public enum PriorityType {

    URGENT("Urgent"),
    LOW("Insignifiant"),
    HIGH("priorité");

    private String label;

    PriorityType(String label) {
        this.label = label;
    }
}
