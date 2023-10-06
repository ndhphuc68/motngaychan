package com.ndhphuc.motngaythu6.service;

import com.ndhphuc.motngaythu6.dto.AuthenticationDTO;
import com.ndhphuc.motngaythu6.model.Role;
import com.ndhphuc.motngaythu6.model.User;
import com.ndhphuc.motngaythu6.repository.RoleRepository;
import com.ndhphuc.motngaythu6.repository.UserRepository;
import com.ndhphuc.motngaythu6.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    public boolean createUser(AuthenticationDTO authenticationDTO) {
        User user = userRepository.findByUsername(authenticationDTO.getUsername());
        if (user == null) {
            User userCreate = new User();
            userCreate.setName(authenticationDTO.getName());
            userCreate.setUsername(authenticationDTO.getUsername());
            userCreate.setPassword(encoder.encode(authenticationDTO.getPassword()));
            userCreate.setCreateDate(new Date());

            Set<Role> roles = new HashSet<>();
            Set<String> strRoles = authenticationDTO.getRole();

            if (strRoles == null) {
                Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);

                            break;
                        case "mod":
                            Role modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);

                            break;
                        default:
                            Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }

            userCreate.setRoles(roles);
            userRepository.save(userCreate);
            return true;
        }
        return false;
    }
}
