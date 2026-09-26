package com.emcee.howzzapp.user.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emcee.howzzapp.user.dto.req.SendOtpRequest;
import com.emcee.howzzapp.user.dto.req.UserRegRequest;
import com.emcee.howzzapp.user.dto.res.UserRegResponse;
import com.emcee.howzzapp.user.entity.UserProfile;
import com.emcee.howzzapp.user.entity.Users;
import com.emcee.howzzapp.user.helper.UserRegHelper;
import com.emcee.howzzapp.user.repository.UserProfileRepository;
import com.emcee.howzzapp.user.repository.UsersRepository;
import com.emcee.howzzapp.user.service.OtpService;
import com.emcee.howzzapp.user.service.UserRegService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the UserRegService interface
 * UserRegServiceImpl
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserRegServiceImpl implements UserRegService {

        private final UsersRepository usersRepository;
        private final UserProfileRepository userProfileRepository;
        private final PasswordEncoder passwordEncoder;
        private final OtpService otpService;

        /*
         * (non-Javadoc)
         * 
         * @see
         * com.emcee.howzzapp.user.service.UserRegService#registerUser(com.emcee.
         * howzzapp
         * .user.dto.req.UserRegRequest)
         */
        @Override
        @Transactional
        public UserRegResponse registerUser(UserRegRequest userRegRequest) {
                log.info("Registering user: {}", userRegRequest);

                // Check if the username already exists
                if (usersRepository.existsByUsername(userRegRequest.username())) {
                        throw new RuntimeException(
                                        String.format("Username '%s' already exists.", userRegRequest.username()));
                }

                // Check if the email already exists
                if (usersRepository.existsByEmail(userRegRequest.email())) {
                        throw new RuntimeException(
                                        String.format("Email '%s' already exists.", userRegRequest.email()));
                }

                // Check if the mobile number already exists (if provided)
                if (userRegRequest.mobileNumber() != null
                                && usersRepository.existsByMobileNumber(userRegRequest.mobileNumber())) {
                        throw new RuntimeException(
                                        String.format("Mobile number '%s' already exists.",
                                                        userRegRequest.mobileNumber()));
                }

                // Create and save the Users entity
                Users user = UserRegHelper.getUserEnityFromRequest(
                                userRegRequest, passwordEncoder.encode(userRegRequest.password()));
                Users savedUser = usersRepository.save(user);

                // Create and save the UserProfile entity
                UserProfile userProfile = UserRegHelper.getUserProfileEntityFromRequest(
                                userRegRequest, savedUser.getId());
                userProfileRepository.save(userProfile);

                // Send OTP for user registration
                SendOtpRequest sendOtpRequest = UserRegHelper.getSendOtpRequest(
                                savedUser.getId(), userRegRequest.username(), userRegRequest.email());
                otpService.sendOtp(sendOtpRequest);

                return UserRegResponse.of(
                                savedUser.getId(),
                                userRegRequest.username(),
                                userRegRequest.email(),
                                "User registered successfully. Validate the OTP sent to your email to activate your account.");

        }

}
