package com.examly.springapp.dto; // <-- must match folder structure

public class AuthResponse {
    private String token;
    private String role; // add this for frontend role redirect

    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() { return token; }
    public String getRole() { return role; }
}