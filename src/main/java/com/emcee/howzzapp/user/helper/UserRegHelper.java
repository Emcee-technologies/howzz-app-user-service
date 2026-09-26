package com.emcee.howzzapp.user.helper;

import java.time.LocalDateTime;
import java.util.UUID;

import com.emcee.howzzapp.user.constant.UserServiceConstant;
import com.emcee.howzzapp.user.dto.req.SendOtpRequest;
import com.emcee.howzzapp.user.dto.req.UserRegRequest;
import com.emcee.howzzapp.user.entity.UserProfile;
import com.emcee.howzzapp.user.entity.Users;

/**
 * 
 * UserRegHelper
 */
public class UserRegHelper {

    /**
     * Converts a user registration request to a user entity
     * 
     * @param userRegRequest
     * @param passwordHash
     * @return Users
     */
    public static Users getUserEnityFromRequest(
            UserRegRequest userRegRequest,
            String passwordHash) {
        Users user = new Users();
        user.setUsername(userRegRequest.username());
        user.setEmail(userRegRequest.email());
        user.setMobileNumber(userRegRequest.mobileNumber());
        user.setPasswordHash(passwordHash);
        return user;
    }

    /**
     * Converts a user registration request to a user profile entity
     * 
     * @param userRegRequest
     * @param savedUserUserId
     * @return UserProfile
     */
    public static UserProfile getUserProfileEntityFromRequest(
            UserRegRequest userRegRequest,
            UUID savedUserUserId) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(savedUserUserId);
        userProfile.setFirstName(userRegRequest.firstName());
        userProfile.setLastName(userRegRequest.lastName());
        userProfile.setDisplayName(String.format(
                "%s %s", userRegRequest.firstName(), userRegRequest.lastName()));
        return userProfile;
    }

    /**
     * Creates a send OTP request for the user registration
     * 
     * @param userId
     * @param userName
     * @param email
     * @return SendOtpRequest
     */
    public static SendOtpRequest getSendOtpRequest(
            UUID userId, String userName, String email) {
        return new SendOtpRequest(
                userId,
                userName,
                UserServiceConstant.OTP_TYPE_REGISTRATION,
                UserServiceConstant.OTP_CHANNEL_EMAIL,
                email,
                LocalDateTime.now().plusMinutes(UserServiceConstant.REGISTRATION_OTP_EXPIRY_MINUTES));
    }

    private UserRegHelper() {
        // Private constructor to prevent instantiation
    }
}
