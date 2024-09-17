package com.example.springjdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftTypes extends DefaultTable{
    private UUID id;
    private String uqName;
    private String description;
    private boolean active;
    private String timeZone;
    private UUID tenantId;
}