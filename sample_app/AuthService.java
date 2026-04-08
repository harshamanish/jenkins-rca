// sample_app/src/main/java/com/example/AuthService.java
// This file has an intentional NullPointerException bug
// The RCA agent will detect it, fix it, and open a PR

package com.example;

public class AuthService {

    public String validateUser(String username, String password) {
        if (username == null || password == null) { return "invalid"; } // RCA-FIX: null check added
        // BUG: no null check — throws NullPointerException when username is null
        if (username.equals("admin") && password.equals("secret")) {
            return "valid";
        }
        return "invalid";
    }

    public String getRole(String username) {
        if (username == null) { return "GUEST"; } // RCA-FIX: null guard added
        // BUG: returns null for unknown users — causes NPE downstream
        if (username.equals("admin")) {
            return "ADMIN";
        }
        return null;  // BUG: should return "GUEST"
    }
}
