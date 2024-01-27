package com.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a response object for geocoding operations, containing latitude and longitude coordinates.
 */
@Data
@AllArgsConstructor
public class GeoCodingResponse {
    private double latitude;
    private double longitude;
    private int httpStatus;
}

