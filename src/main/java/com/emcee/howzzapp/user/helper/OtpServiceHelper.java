package com.emcee.howzzapp.user.helper;

import com.emcee.howzzapp.user.dto.req.SendOtpRequest;
import com.emcee.howzzapp.user.entity.Otp;

/**
 * OtpServiceHelper
 */
public class OtpServiceHelper {

    /**
     * 
     * @param request
     * @param otpHash
     * @return Otp
     */
    public static Otp getOtpEntity(
            SendOtpRequest request,
            String otpHash) {
        Otp otpEntity = new Otp();
        otpEntity.setUserId(request.userId());
        otpEntity.setOtpType(request.otpType());
        otpEntity.setChannel(request.channel());
        otpEntity.setRecipient(request.recipient());
        otpEntity.setOtpHash(otpHash);
        otpEntity.setExpiresAt(request.expiresAt());
        return otpEntity;
    }
}
