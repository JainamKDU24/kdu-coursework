package com.example.springexercise4.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * ResponseDTO for returining the JSON data requested in the API call
 */
@Builder
@Data
@AllArgsConstructor
public class VehicleResponseDTO {
    private int id;
    private String name;
    private String  tyre;
    private String speaker;
    private double price;
}
