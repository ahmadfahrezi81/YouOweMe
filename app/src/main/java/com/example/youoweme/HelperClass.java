package com.example.youoweme;

public class HelperClass {

    String name, phone, username, password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setEmail(String phone) {
        this.phone = phone;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public HelperClass(String name, String email, String username) {
        this.name = name;
        this.phone = phone;
        this.username = username;
    }
}