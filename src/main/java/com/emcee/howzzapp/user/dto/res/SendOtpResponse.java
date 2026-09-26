package com.emcee.howzzapp.user.dto.res;

import java.time.LocalDateTime;

/**
 * 
 * SendOtpResponse
 * 
 * @param otpType
 * @param recipient
 * @param expiresAt
 */
public record SendOtpResponse(
                String otpType, String recipient, LocalDateTime expiresAt) {

        /**
         * 
         * @param otpType
         * @param recipient
         * @param expiresAt
         * @return SendOtpResponse
         */
        public static SendOtpResponse of(String otpType, String recipient, LocalDateTime expiresAt) {
                return new SendOtpResponse(otpType, recipient, expiresAt);
        }

}
