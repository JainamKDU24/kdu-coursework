package com.kdu.smarthome.dto.reponse;

import com.kdu.smarthome.entity.House;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseResponseDTO {
    String message;
    House house;
    HttpStatus httpStatus;
}
