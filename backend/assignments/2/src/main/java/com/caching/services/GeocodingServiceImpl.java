package com.caching.services;

import com.caching.dto.GeoCodingResponse;
import com.caching.exceptions.custom.MyException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class GeocodingServiceImpl implements GeoCodingService {
    private static final Logger logger = LoggerFactory.getLogger(GeocodingServiceImpl.class);

    @Value("${api-key}")
    private String apiKey;

    @Autowired
    private CacheManager caffeineCacheManager;

    /**
     * Retrieves a geocoding response from cache if available, otherwise retrieves it from the API.
     *
     * @param address The address to geocode.
     * @return The geocoding response.
     */
    @Override
    public GeoCodingResponse checkfromCache1(String address) {
        // Check if response is available in cache
        Cache cache = caffeineCacheManager.getCache("geocoding");
        if (cache != null) {
            Cache.ValueWrapper cachedResponse = cache.get(address);
            if (cachedResponse != null) {
                logger.info("Response retrieved using cache for address: {}", address);
                return (GeoCodingResponse) cachedResponse.get();
            }
        }
        return getGeoCoding(address);
    }

    /**
     * Retrieves the geocoding response from the API and caches it.
     *
     * @param address The address to geocode.
     * @return The geocoding response.
     */
    @Override
    @Cacheable(value = "geocoding", key = "#address", cacheManager = "caffeineCacheManager", unless = "address.toLowerCase().contains(\"goa\")")
    public GeoCodingResponse getGeoCoding(String address) {
        GeoCodingResponse geoCodingResponse = new GeoCodingResponse(0, 0, 0);

        try {
            String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
            String apiUrl = "http://api.positionstack.com/v1/forward?access_key=" + apiKey +
                    "&query=" + encodedAddress;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parses the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray data = jsonResponse.getJSONArray("data");
                if (data.length() > 0) {
                    JSONObject firstResult = data.getJSONObject(0);

                    // Extracts latitude and longitude values
                    double latitude = firstResult.getDouble("latitude");
                    double longitude = firstResult.getDouble("longitude");

                    // Set latitude, longitude, and HTTP status in the response object
                    geoCodingResponse.setLatitude(latitude);
                    geoCodingResponse.setLongitude(longitude);
                    geoCodingResponse.setHttpStatus(responseCode);

                    // Caches the response if the address is not in Goa
                    if (!address.toLowerCase().contains("goa")) {
                        logger.info("Caching the current record for address: {}", address);
                        caffeineCacheManager.getCache("geocoding").put(address, geoCodingResponse);
                        logger.info("Latitude: {}, longitude: {} for address : {}",latitude,longitude,address);
                    }
                } else {
                    throw new MyException(HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            } else {
                logger.info("GET request failed");
                throw new MyException(HttpStatus.BAD_REQUEST.getReasonPhrase());
            }
            connection.disconnect();
        } catch (HttpClientErrorException | IOException e) {
            logger.error(String.valueOf(e));
        }
        logger.info("Response retrieved using 3rd party API for address: {}", address);
        return geoCodingResponse;
    }
}
