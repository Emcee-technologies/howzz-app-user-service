package com.emcee.howzzapp.user.constant;

/**
 * 
 * UserServiceConstant
 */
public class UserServiceConstant {

    public static final String USER_STATUS_INACTIVE = "INACTIVE";
    public static final String USER_STATUS_ACTIVE = "ACTIVE";

    public static final String OTP_TYPE_REGISTRATION = "REGISTRATION";

    public static final String OTP_CHANNEL_EMAIL = "EMAIL";

    public static final int REGISTRATION_OTP_EXPIRY_MINUTES = 3;

    private UserServiceConstant() {
        // Private constructor to prevent instantiation
    }
}
