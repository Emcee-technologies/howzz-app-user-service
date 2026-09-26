package com.emcee.howzzapp.user.service;

import com.emcee.howzzapp.user.dto.req.UserRegRequest;
import com.emcee.howzzapp.user.dto.res.UserRegResponse;

/**
 * UserRegService
 */
public interface UserRegService {

    public UserRegResponse registerUser(UserRegRequest userRegRequest);
}
