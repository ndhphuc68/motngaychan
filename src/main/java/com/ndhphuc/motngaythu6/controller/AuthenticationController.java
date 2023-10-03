package com.ndhphuc.motngaythu6.controller;

import com.ndhphuc.motngaythu6.config.jwt.JwtUtils;
import com.ndhphuc.motngaythu6.config.security.UserDetailsImpl;
import com.ndhphuc.motngaythu6.dto.ApiResponse;

import com.ndhphuc.motngaythu6.dto.AuthenticationDTO;
import com.ndhphuc.motngaythu6.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Authentication Controller")
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/login")
    public ApiResponse login(@RequestBody AuthenticationDTO authenticationDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (authenticationDTO.getUsername() == null || authenticationDTO.getPassword() == null) {
                apiResponse.setSuccess(false);
                return apiResponse;
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            apiResponse.setSuccess(true);
            Map<String, Object> map = new HashMap<>();
            map.put("token", jwt);
            map.put("roles", roles);
            map.put("username", userDetails.getUsername());
            apiResponse.setData(map);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @PostMapping(value = "/signup")
    public ApiResponse signup(@RequestBody AuthenticationDTO authenticationDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (authenticationDTO == null) {
                apiResponse.setSuccess(false);
                apiResponse.setMessage("Data null");
                return apiResponse;
            }
            apiResponse.setSuccess(authenticationService.createUser(authenticationDTO));
            apiResponse.setMessage("Create Success");
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }
}
