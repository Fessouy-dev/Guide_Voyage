package com.monTour.guidevoyage.service;

import com.monTour.guidevoyage.dto.CircuitRequest;
import com.monTour.guidevoyage.dto.LocationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TourismeApiService {

    private final RestTemplate restTemplate;
    private final String apiBaseUrl;

    public TourismeApiService(
            RestTemplate restTemplate,
            @Value("${tourisme.api.url:http://localhost:8001}") String apiBaseUrl) {
        this.restTemplate = restTemplate;
        this.apiBaseUrl = apiBaseUrl;
    }

    public Object recommendLocations(LocationRequest request) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/recommend")
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LocationRequest> entity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(url, entity, Object.class);
    }

    public Object generateCircuit(CircuitRequest request) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/generate-circuit")
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CircuitRequest> entity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(url, entity, Object.class);
    }
}
