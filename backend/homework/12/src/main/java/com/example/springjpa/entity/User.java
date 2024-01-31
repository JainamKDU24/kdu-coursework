package com.example.springjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends DefaultTags {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "loggedin")
    private short loggedIn;

    @Column(name = "time_zone")
    private String timeZone;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}
