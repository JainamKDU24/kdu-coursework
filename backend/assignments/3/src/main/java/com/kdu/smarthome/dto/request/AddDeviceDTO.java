package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDeviceDTO {
    private String kickstonId;

    private String houseid;

    private String roomid;
}
