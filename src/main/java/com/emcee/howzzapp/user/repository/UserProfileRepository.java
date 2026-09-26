package com.emcee.howzzapp.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emcee.howzzapp.user.entity.UserProfile;

/**
 * Repository interface for managing UserProfile entities.
 * UserProfileRepository
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

}
