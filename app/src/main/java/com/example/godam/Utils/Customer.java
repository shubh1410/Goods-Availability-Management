package com.example.godam.Utils;

public class Customer
{
    private String role;
    private String email;

    public Customer(String email, String role)
    {
        this.role = role;
        this.email = email;
    }

    public Customer()
    {
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
