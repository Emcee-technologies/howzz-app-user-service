package com.emcee.howzzapp.user.service;

import com.emcee.howzzapp.user.dto.req.SendOtpRequest;
import com.emcee.howzzapp.user.dto.res.SendOtpResponse;

/**
 * 
 * OtpService
 */
public interface OtpService {

    public SendOtpResponse sendOtp(SendOtpRequest request);

    public boolean verifyOtp(String recipient, String otpType, String otp);
}
