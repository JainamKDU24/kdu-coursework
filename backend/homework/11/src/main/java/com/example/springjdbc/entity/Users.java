package com.example.springjdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users extends DefaultTable{
    private UUID id;
    private String username;
    private short loggedIn;
    private String timeZone;
    private UUID tenantId;
}
