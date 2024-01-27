package com.caching.contoller;

import com.caching.dto.GeoCodingResponse;
import com.caching.services.GeoCodingService;
import com.caching.services.RevGeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller class for handling API endpoints related to geocoding and reverse geocoding.
 */
@RestController
public class ApiController {

    @Autowired
    private GeoCodingService geoCodingService;

    @Autowired
    private RevGeoCodingService revGeoCodingService;

    /**
     * Endpoint for geocoding based on the provided address.
     * @param address The address to geocode.
     * @return The geocoding response.
     */
    @GetMapping("/geocoding")
    public GeoCodingResponse geocoding(@RequestParam String address) {
        return geoCodingService.checkfromCache1(address);
    }

    /**
     * Endpoint for reverse geocoding based on the provided latitude and longitude.
     * @param latitude The latitude coordinate.
     * @param longitude The longitude coordinate.
     * @return The reverse geocoding response.
     */
    @GetMapping("/reverse-geocoding")
    public String reverseGeocoding(@RequestParam("latitude") double latitude,
                                   @RequestParam("longitude") double longitude) {
        return revGeoCodingService.checkfromCache2(latitude, longitude).getAddress();
    }
}