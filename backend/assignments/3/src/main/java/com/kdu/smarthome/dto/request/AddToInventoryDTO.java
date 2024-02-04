package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddToInventoryDTO {
    private String kickston_id;
    private String device_username;
    private String device_password;
    private String manufacture_date_time;
    private String manufacture_factory_place;
}
