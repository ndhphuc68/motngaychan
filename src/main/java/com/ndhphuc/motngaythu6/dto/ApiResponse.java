package com.ndhphuc.motngaythu6.dto;

import lombok.Getter;

@Getter
public class ApiResponse {
    private Boolean success;

    private String message;

    private String error;

    private Object data;

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setError(String error) {
        this.error = error;
    }
}
