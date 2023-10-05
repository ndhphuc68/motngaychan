package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

import java.util.Set;

@Getter
public class AuthenticationDTO {

    private String username;

    private String password;

//    private String name;
//
//    private Set<String> role;

//    public void setRole(Set<String> role) {
//        this.role = role;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
