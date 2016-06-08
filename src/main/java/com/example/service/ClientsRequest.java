package com.example.service;

import com.example.ResponseObjects.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.*;

/**
 * Created by Maciej on 2016-05-26.
 */
public class ClientsRequest {

    @Value("${timeout.request.read}")
    private int readTimeout;

    @Value("${timeout.request.connect}")
    private int connectionTimeout;

    private RestTemplate restTemplate;
    private List<JobsResponse> jobsResponse;
    private ObjectMapper mapper;

    public ClientsRequest() {
        restTemplate = new RestTemplate();
        jobsResponse = new ArrayList<>();
        mapper = new ObjectMapper();
        initialize();
    }

    private void initialize() {
        ((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setReadTimeout(readTimeout);
        ((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setConnectTimeout(connectionTimeout);
    }

    public List<JobsResponse> sendRequest(String url) {
        try {
            ResponseEntity<List<JobsResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<JobsResponse>>() {});
            return response.getBody();
        } catch (ResourceAccessException e) {
            return null;
        }
    }

    public List<LocationResponse> doLocationJob(String url){
        try {
            ResponseEntity<List<LocationResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<LocationResponse>>() {});
            return response.getBody();
        } catch (ResourceAccessException e) {
            return null;
        }
    }

    public List<UniversityResponse> doUniversityJob(String url){
        try {
            ResponseEntity<List<UniversityResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UniversityResponse>>() {});
            return response.getBody();
        } catch (ResourceAccessException e) {
            return null;
        }
    }

    public List<FieldOfStudyResponse> doFieldOfStudyJob(String url){
        try {
            ResponseEntity<List<FieldOfStudyResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<FieldOfStudyResponse>>() {});
            return response.getBody();
        } catch (ResourceAccessException e) {
            return null;
        }
    }

    public List<ComeFromResponse> doComeFromJob(String url){
        try {
            ResponseEntity<List<ComeFromResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ComeFromResponse>>() {});
            return response.getBody();
        } catch (ResourceAccessException e) {
            return null;
        }

    }

    public List<OriginFromCountriesResponse> doOriginFromCountriesJob(String url){
        try {
            ResponseEntity<List<OriginFromCountriesResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<OriginFromCountriesResponse>>() {});
            return response.getBody();
        } catch (ResourceAccessException e) {
            return null;
        }
    }

    public List<OriginFromUniversitiesResponse> doOriginFromUniversitiesJob(String url){
        try {
            ResponseEntity<List<OriginFromUniversitiesResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<OriginFromUniversitiesResponse>>() {});
            return response.getBody();
        } catch (ResourceAccessException e) {
            return null;
        }
    }

    public List<OriginFromStudiesResponse> doOriginFromStudiesJob(String url){
        try {
            ResponseEntity<List<OriginFromStudiesResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<OriginFromStudiesResponse>>() {});
            return response.getBody();
        } catch (ResourceAccessException e) {
            return null;
        }
    }

    public List<WorkingUniversitiesResponse> doWorkingUniversitiesJob(String url){
        try {
            ResponseEntity<List<WorkingUniversitiesResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<WorkingUniversitiesResponse>>() {});
            return response.getBody();
        } catch (ResourceAccessException e) {
            return null;
        }
    }
}
