package com.kdu.smarthome.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO {
    private String message;
    private Object object;
    private HttpStatus httpStatus;

}
