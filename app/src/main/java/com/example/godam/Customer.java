package com.example.godam;

public class Customer {
    private String role;
    private String email;

    public Customer(String role, String email) {
        this.role = role;
        this.email = email;
    }

    public Customer() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
