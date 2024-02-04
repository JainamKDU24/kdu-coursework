package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTO {
    private String house_name;
    private String address;
}
