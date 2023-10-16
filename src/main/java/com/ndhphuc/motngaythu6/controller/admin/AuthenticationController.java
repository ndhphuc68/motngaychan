package com.ndhphuc.motngaythu6.controller.admin;

import com.ndhphuc.motngaythu6.config.jwt.JwtUtils;
import com.ndhphuc.motngaythu6.config.security.UserDetailsImpl;
import com.ndhphuc.motngaythu6.dto.ApiResponse;

import com.ndhphuc.motngaythu6.dto.AuthenticationDTO;
import com.ndhphuc.motngaythu6.dto.LoginDTO;
import com.ndhphuc.motngaythu6.model.Role;
import com.ndhphuc.motngaythu6.model.User;
import com.ndhphuc.motngaythu6.repository.RoleRepository;
import com.ndhphuc.motngaythu6.repository.UserRepository;
import com.ndhphuc.motngaythu6.service.AuthenticationService;
import com.ndhphuc.motngaythu6.utils.RoleEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "Authentication Admin Controller")
@RestController
@RequestMapping(value = "/api/v1/auth/admin")
@CrossOrigin(origins = "*")
public class AuthenticationController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  AuthenticationService authenticationService;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @PostMapping(value = "/login")
  public ApiResponse login(@RequestBody LoginDTO authenticationDTO) {
    ApiResponse apiResponse = new ApiResponse();
    try {
      if (authenticationDTO.getUsername() == null || authenticationDTO.getPassword() == null) {
        apiResponse.setSuccess(false);
        return apiResponse;
      }

      User user = userRepository.findByUsername(authenticationDTO.getUsername());

      if (user == null) {
        apiResponse.setSuccess(false);
        apiResponse.setMessage("User NotFound");
        return apiResponse;
      }
      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      if (!passwordEncoder.matches(authenticationDTO.getPassword(), user.getPassword())) {
        apiResponse.setSuccess(false);
        apiResponse.setMessage("Password NotFound");
        return apiResponse;
      }

      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.toList());

      for (String role : roles) {
        if (role.equals("ROLE_ADMIN")) {
          apiResponse.setSuccess(true);
          Map<String, Object> map = new HashMap<>();
          map.put("token", jwt);
          map.put("roles", roles);
          map.put("username", userDetails.getUsername());
          apiResponse.setData(map);
          return apiResponse;
        }
      }
      apiResponse.setSuccess(false);
      apiResponse.setMessage("Account has no permissions");

    } catch (Exception e) {
      apiResponse.setSuccess(false);
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
