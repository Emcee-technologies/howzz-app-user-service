package com.emcee.howzzapp.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emcee.howzzapp.user.entity.Otp;

/**
 * OtpRepository
 */
@Repository
public interface OtpRepository extends JpaRepository<Otp, UUID> {

    /**
     * Find an OTP entity by recipient and OTP type.
     *
     * @param recipient the recipient of the OTP
     * @param otpType   the type of the OTP
     * @return the found Otp entity, or null if not found
     */
    Optional<Otp> findByRecipientAndOtpType(String recipient, String otpType);

}
