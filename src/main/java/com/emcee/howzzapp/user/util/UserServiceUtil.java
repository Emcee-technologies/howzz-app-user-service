package com.emcee.howzzapp.user.util;

/**
 * 
 * UserServiceUtil
 */
public class UserServiceUtil {

    /**
     * Generate a random 6-digit OTP
     * 
     * @return int - 6-digit OTP
     */
    public static int generateOtp() {
        // Generate a random 6-digit OTP
        return (int) (Math.random() * 900000) + 100000;
    }

    private UserServiceUtil() {
        // Private constructor to prevent instantiation
    }
}
