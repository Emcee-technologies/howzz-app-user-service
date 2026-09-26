package com.emcee.howzzapp.user.dto.req;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 
 * SendOtpRequest
 * 
 * @param userId
 * @param userName
 * @param otpType
 * @param channel
 * @param recipient
 * @param expiresAt
 */
public record SendOtpRequest(
        UUID userId, String userName, String otpType, String channel, String recipient, LocalDateTime expiresAt) {

}
