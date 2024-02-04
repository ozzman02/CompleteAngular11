package com.taskmanager.controller;

import com.taskmanager.service.ClientLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.taskmanager.constants.ApplicationConstants.CLIENT_LOCATIONS_API_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(CLIENT_LOCATIONS_API_URL)
public class ClientLocationController {

    private final ClientLocationService clientLocationService;

    @GetMapping
    public ResponseEntity<?> getClientLocations() {
        return new ResponseEntity<>(clientLocationService.findAll(), HttpStatus.OK);
    }

}
