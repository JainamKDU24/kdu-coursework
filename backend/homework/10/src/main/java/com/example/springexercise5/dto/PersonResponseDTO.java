package com.example.springexercise5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PersonResponseDTO {
    private String name;
    private String email;
    private String role;
}
