package com.emcee.howzzapp.user.dto.res;

import java.util.UUID;

/**
 * Data Transfer Object for user registration response
 * UserRegResponse
 * 
 * @param userId
 * @param username
 * @param email
 * @param otp
 */
public record UserRegResponse(
                UUID userId,
                String username,
                String email,
                String message) {

        /**
         * Factory method to create a UserRegResponse instance
         * 
         * @param userId
         * @param username
         * @param email
         * @param message
         * @return UserRegResponse
         */
        public static UserRegResponse of(UUID userId, String username, String email, String message) {
                return new UserRegResponse(userId, username, email, message);
        }
}
