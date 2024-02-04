package com.kdu.smarthome.entity;

import com.kdu.smarthome.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "house_users")
public class HouseUser {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String houseUserId;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Role role;
}
