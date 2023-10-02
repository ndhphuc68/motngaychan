package com.ndhphuc.motngaythu6.controller;

import com.ndhphuc.motngaythu6.model.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Controller")
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

    @PostMapping(value = "/login")
    public ApiResponse login() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }
}
