package kdu.assessment.assessment2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) class for representing a response containing person details.
 */
@Data
@Builder
@AllArgsConstructor
public class UserResponseDTO {
    /**
     * The name of the person.
     */
    private String fullName;

    /**
     * The email address of the person.
     */
    private String email;

    /**
     * The role of the person.
     */
    private String role;
}
