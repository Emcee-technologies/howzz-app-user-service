package com.emcee.howzzapp.user.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for user registration request
 * UserRegRequest
 * 
 * @param username
 * @param email
 * @param mobileNumber
 * @param password
 * @param firstName
 * @param lastName
 */
public record UserRegRequest(

        @NotBlank @Size(max = 50) String username,

        @NotBlank @Size(max = 255) String email,

        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$") String mobileNumber,

        @NotBlank @Size(max = 100) String password,

        @NotBlank @Size(max = 100) String firstName,

        @Size(max = 100) String lastName) {
}
