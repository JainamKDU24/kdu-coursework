package com.kdu.smarthome.dto.reponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetInventoryDTO {
    String inventory;
    HttpStatus httpStatus;
}

