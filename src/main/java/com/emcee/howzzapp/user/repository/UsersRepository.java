package com.emcee.howzzapp.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emcee.howzzapp.user.entity.Users;

/**
 * Repository interface for managing Users entities.
 * UsersRepository
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {

    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username to check
     * @return true if a user with the given username exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user with the given email exists.
     *
     * @param email the email to check
     * @return true if a user with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Checks if a user with the given mobile number exists.
     *
     * @param mobileNumber the mobile number to check
     * @return true if a user with the given mobile number exists, false otherwise
     */
    boolean existsByMobileNumber(String mobileNumber);

}
