package com.caching.services;

import com.caching.dto.GeoCodingResponse;
import org.springframework.stereotype.Service;

/**
 * Interface for geocoding service.
 */
@Service
public interface GeoCodingService {

    /**
     * Retrieves a geocoding response from cache if available, otherwise retrieves it from the API.
     *
     * @param address The address for geocoding.
     * @return The geocoding response.
     */
    GeoCodingResponse checkfromCache1(String address);

    /**
     * Retrieves the geocoding response from the API and caches it.
     *
     * @param address The address for geocoding.
     * @return The geocoding response.
     */
    GeoCodingResponse getGeoCoding(String address);
}
