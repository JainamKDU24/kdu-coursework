package com.kdu.smarthome.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventories")
public class Inventory extends CommonTags {
    @Id
    @Column(length = 6)
    private String kickstonid;
    private String deviceusername;
    private String devicepassword;
    private String manufacturedatetime;
    private String manufacturefactoryplace;
}