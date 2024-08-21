package com.example.springexercise4.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * RequestDTO for storing the JSON data pushed in the API call
 */
@Data
@AllArgsConstructor
public class VehicleRequestDTO {

    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String tyre;
    @NonNull
    private String  speaker;
    @NonNull
    private double price;
}
