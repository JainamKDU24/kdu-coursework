package com.kdu.smarthome.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "rooms")
public class Room extends CommonTags {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("id")
    private String roomId;

    private String room_name;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", room_name='" + room_name + '\'' +
                ", house=" + house +
                '}';
    }
}