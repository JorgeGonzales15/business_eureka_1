package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.response.RatingResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IRatingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Tag(name = "Rating Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class RatingController {
    private final IRatingService ratingService;
    private final RestTemplate restTemplate;
    private final String fallbackServiceUrl = "http://localhost:8010"; // Cambia esto con la URL de tu servicio en Azure

    @Autowired
    public RatingController(IRatingService ratingService, RestTemplate restTemplate) {
        this.ratingService = ratingService;
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "BusinessCB", fallbackMethod = "fallBackPostRating")
    @PostMapping("/ratings/{idCompany}")
    public ResponseEntity<RatingResponseDto> createRating(@PathVariable Long idCompany, @RequestBody RatingRequestDto ratingRequestDto) {
        var res = ratingService.createRating(idCompany, ratingRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @CircuitBreaker(name = "BusinessCB", fallbackMethod = "fallBackGetRating")
    @GetMapping("/ratings/company/{idCompany}")
    public ResponseEntity<?> getRatingsByCompanyId(@PathVariable Long idCompany) {
        var res = ratingService.getRatingsByCompanyId(idCompany);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<RatingResponseDto> fallBackPostRating(Long idCompany, RatingRequestDto ratingRequestDto, Throwable throwable) {
        // Log the error for debugging purposes
        System.err.println("Error during createRating: " + throwable.getMessage());

        // Call the fallback service in Azure
        try {
            String url = fallbackServiceUrl + "/api/v1/ratings/" + idCompany;
            ResponseEntity<RatingResponseDto> response = restTemplate.postForEntity(url, ratingRequestDto, RatingResponseDto.class);
            return response;
        } catch (Exception e) {
            System.err.println("Error during fallback call: " + e.getMessage());
            // Return an appropriate error response
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public ResponseEntity<?> fallBackGetRating(Long idCompany, Throwable throwable) {
        // Log the error for debugging purposes
        System.err.println("Error during getRatingsByCompanyId: " + throwable.getMessage());

        // Call the fallback service in Azure
        try {
            String url = fallbackServiceUrl + "/api/v1/ratings/company/" + idCompany;
            ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);
            return response;
        } catch (Exception e) {
            System.err.println("Error during fallback call: " + e.getMessage());
            // Return an appropriate error response
            return new ResponseEntity<>(Collections.singletonMap("message", "Fallback: Unable to retrieve ratings at this time. Please try again later."), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
