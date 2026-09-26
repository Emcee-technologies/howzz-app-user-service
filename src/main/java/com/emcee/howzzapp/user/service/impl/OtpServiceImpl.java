package com.emcee.howzzapp.user.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emcee.howzzapp.commonfwk.util.CommonUtil;
import com.emcee.howzzapp.user.dto.PublishToKafkaDTO;
import com.emcee.howzzapp.user.dto.req.SendOtpRequest;
import com.emcee.howzzapp.user.dto.res.SendOtpResponse;
import com.emcee.howzzapp.user.entity.Otp;
import com.emcee.howzzapp.user.exception.OtpException;
import com.emcee.howzzapp.user.helper.OtpServiceHelper;
import com.emcee.howzzapp.user.repository.OtpRepository;
import com.emcee.howzzapp.user.service.KafkaPublisher;
import com.emcee.howzzapp.user.service.OtpService;
import com.emcee.howzzapp.user.util.UserServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * OtpServiceImpl
 */
@Slf4j
@Service
public class OtpServiceImpl implements OtpService {

    private static final int MAX_OTP_ATTEMPTS = 3;

    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    private final KafkaPublisher kafkaPublisher;

    private final String notificationTopic;

    public OtpServiceImpl(
            OtpRepository otpRepository,
            PasswordEncoder passwordEncoder,
            KafkaPublisher kafkaPublisher,
            @Value("${spring.kafka.producer.notification-q}") String notificationTopic) {
        super();
        this.otpRepository = otpRepository;
        this.passwordEncoder = passwordEncoder;
        this.kafkaPublisher = kafkaPublisher;
        this.notificationTopic = notificationTopic;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.emcee.howzzapp.user.service.OtpService#sendOtp(com.emcee.howzzapp.user.
     * dto.req.SendOtpRequest)
     */
    @Override
    public SendOtpResponse sendOtp(SendOtpRequest request) {
        log.info("Sending OTP to user: {}", request);

        // checking if the same otp is present already
        Optional<Otp> otpOption = otpRepository.findByRecipientAndOtpType(request.recipient(), request.otpType());

        if (otpOption.isPresent()) {
            Otp existingOtp = otpOption.get();
            boolean isOtpActive = existingOtp.getExpiresAt().isAfter(LocalDateTime.now())
                    && CommonUtil.nullOrDefault(existingOtp.getAttemptCount(), 0) < MAX_OTP_ATTEMPTS;
            if (isOtpActive) {
                throw new OtpException(
                        String.format("Active OTP already exists for recipient: %s and otpType: %s",
                                request.recipient(), request.otpType()));
            }
        }

        // sending new otp
        Otp otpEntity = OtpServiceHelper.getOtpEntity(
                request, passwordEncoder.encode(String.valueOf(UserServiceUtil.generateOtp())));
        otpRepository.save(otpEntity);

        // publishing to kafka
        PublishToKafkaDTO publishToKafkaDTO = PublishToKafkaDTO.builder()
                .topicName(notificationTopic)
                .message(otpEntity)
                .build();
        kafkaPublisher.publishToKafka(publishToKafkaDTO);

        return SendOtpResponse.of(
                request.otpType(),
                request.recipient(),
                request.expiresAt());

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.emcee.howzzapp.user.service.OtpService#verifyOtp(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public boolean verifyOtp(String recipient, String otpType, String otp) {
        log.info("Verifying OTP for recipient: {}, otpType: {}", recipient, otpType);

        Otp otpEntity = otpRepository.findByRecipientAndOtpType(recipient, otpType)
                .orElseThrow(() -> new RuntimeException(
                        String.format("OTP not found for recipient: %s and otpType: %s", recipient, otpType)));

        int currentAttemptCount = CommonUtil.nullOrDefault(otpEntity.getAttemptCount(), 0);
        boolean isOtpExpired = otpEntity.getExpiresAt().isBefore(LocalDateTime.now())
                && currentAttemptCount >= MAX_OTP_ATTEMPTS;
        // Check if the OTP has expired
        if (isOtpExpired) {
            throw new RuntimeException(
                    String.format("OTP has expired for recipient: %s and otpType: %s", recipient, otpType));
        }

        // Verify the OTP
        boolean isOtpValid = passwordEncoder.matches(otp, otpEntity.getOtpHash());
        if (isOtpValid) {
            otpEntity.setVerifiedAt(LocalDateTime.now());
        } else {
            otpEntity.setAttemptCount(currentAttemptCount + 1);
        }
        otpRepository.save(otpEntity);

        return isOtpValid;
    }

}
