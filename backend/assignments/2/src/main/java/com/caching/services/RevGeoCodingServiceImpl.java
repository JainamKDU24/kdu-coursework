package com.caching.services;

import com.caching.dto.ReverseGeoCodingResponse;
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
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Service implementation for reverse geocoding operations.
 */
@Slf4j
@Service
public class RevGeoCodingServiceImpl implements RevGeoCodingService {
    private static final Logger logger = LoggerFactory.getLogger(RevGeoCodingServiceImpl.class);

    @Value("${api-key}")
    private String apiKey;

    @Autowired
    private CacheManager caffeineCacheManager;

    /**
     * Retrieves a reverse geocoding response from cache if available, otherwise retrieves it from the API.
     *
     * @param latitude  The latitude coordinate.
     * @param longitude The longitude coordinate.
     * @return The reverse geocoding response.
     */
    @Override
    public ReverseGeoCodingResponse checkfromCache2(double latitude, double longitude) {
        String cacheKey = String.format("%f,%f", latitude, longitude);
        Cache cache = caffeineCacheManager.getCache("reverse-geocoding");
        if (cache != null && cache.get(cacheKey) != null) {
            logger.info("Response retrieved using cache for latitude: {}, longitude: {}", latitude, longitude);
            return (ReverseGeoCodingResponse) cache.get(cacheKey).get();
        } else {
            return getReverseGeoCoding(latitude, longitude);
        }
    }

    /**
     * Retrieves the reverse geocoding response from the API and caches it.
     *
     * @param latitude  The latitude coordinate.
     * @param longitude The longitude coordinate.
     * @return The reverse geocoding response.
     */
    @Override
    @Cacheable(value = "reverse-geocoding", key = "{#latitude, #longitude}", cacheManager = "caffeineCacheManager")
    public ReverseGeoCodingResponse getReverseGeoCoding(double latitude, double longitude) {
        ReverseGeoCodingResponse reverseGeoCodingResponse = null;

        try {
            String apiUrl = "http://api.positionstack.com/v1/reverse?access_key=" + apiKey +
                    "&query=" + latitude + "," + longitude;

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

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray results = jsonResponse.getJSONArray("data");
                if (results.length() > 0) {
                    JSONObject result = results.getJSONObject(0);
                    reverseGeoCodingResponse = new ReverseGeoCodingResponse(
                            result.getString("label")
                    );
                    String cacheKey = String.format("%f,%f", latitude, longitude);
                    caffeineCacheManager.getCache("reverse-geocoding").put(cacheKey, reverseGeoCodingResponse);
                }
            } else {
                logger.info("GET request failed");
            }
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
        return reverseGeoCodingResponse;
    }
}
