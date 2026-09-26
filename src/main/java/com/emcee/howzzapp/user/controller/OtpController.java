package com.emcee.howzzapp.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emcee.howzzapp.user.dto.req.SendOtpRequest;
import com.emcee.howzzapp.user.dto.res.SendOtpResponse;
import com.emcee.howzzapp.user.service.OtpService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * OtpController
 */
@Slf4j
@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class OtpController {

    private final OtpService otpService;

    /**
     * 
     * @param sendOtpRequest
     * @return ResponseEntity<?>
     */
    @PostMapping("/v1/resend-otp")
    public ResponseEntity<?> sendOtp(
            @Valid @RequestBody SendOtpRequest sendOtpRequest) {

        SendOtpResponse sendOtpResponse = otpService.sendOtp(sendOtpRequest);

        String response = String.format("%s OTP sent successfully to %s",
                sendOtpResponse.otpType(), sendOtpResponse.recipient());
        return ResponseEntity.ok().body(response);
    }
}
