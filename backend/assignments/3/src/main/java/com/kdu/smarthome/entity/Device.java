package com.kdu.smarthome.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devices")
public class Device extends CommonTags {
    @Id
    private String kickstonid;

    @Column(nullable = false)
    private String deviceusername;

    @Column(nullable = false)
    private String devicepassword;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

}