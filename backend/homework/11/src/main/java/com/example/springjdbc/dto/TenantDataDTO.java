package com.example.springjdbc.dto;

import com.example.springjdbc.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TenantDataDTO {
    private Tenant tenant;
    private Shifts shift;
    private Users user;
    private ShiftTypes shiftType;
    private ShiftUser shiftUser;

    // Constructors, getters, and setters
}
