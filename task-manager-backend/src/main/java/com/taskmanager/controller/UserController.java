package com.taskmanager.controller;

import com.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.taskmanager.constants.ApplicationConstants.USER_API_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(USER_API_URL)
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getUserDetailsAfterLogin(Authentication authentication) {
        return new ResponseEntity<>(userService.findByUsername(authentication.getName()), HttpStatus.OK);
    }

}
