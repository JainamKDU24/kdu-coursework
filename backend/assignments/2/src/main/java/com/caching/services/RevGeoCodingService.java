package com.caching.services;

import com.caching.dto.ReverseGeoCodingResponse;
import org.springframework.stereotype.Service;

/**
 * Interface for reverse geocoding service.
 */
@Service
public interface RevGeoCodingService {

    /**
     * Retrieves a reverse geocoding response from cache if available, otherwise retrieves it from the API.
     *
     * @param latitude  The latitude coordinate.
     * @param longitude The longitude coordinate.
     * @return The reverse geocoding response.
     */
    ReverseGeoCodingResponse checkfromCache2(double latitude, double longitude);

    /**
     * Retrieves the reverse geocoding response from the API and caches it.
     *
     * @param latitude  The latitude coordinate.
     * @param longitude The longitude coordinate.
     * @return The reverse geocoding response.
     */
    ReverseGeoCodingResponse getReverseGeoCoding(double latitude, double longitude);
}
