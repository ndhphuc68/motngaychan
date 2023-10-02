package com.ndhphuc.motngaythu6.utils;

public enum RoleEnum {
    ADMIN("ADMIN"), USER("USER");

    private String description;

    RoleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
