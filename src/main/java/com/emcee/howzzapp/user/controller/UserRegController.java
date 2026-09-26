package com.emcee.howzzapp.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emcee.howzzapp.user.dto.req.UserRegRequest;
import com.emcee.howzzapp.user.dto.res.UserRegResponse;
import com.emcee.howzzapp.user.service.UserRegService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * UserRegController
 */
@Slf4j
@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class UserRegController {

    private final UserRegService userRegService;

    @PostMapping("/v1/user")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody UserRegRequest userRegRequest) {
        log.info("Registering user: {}", userRegRequest);

        UserRegResponse userRegResponse = userRegService.registerUser(userRegRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userRegResponse);
    }
}
