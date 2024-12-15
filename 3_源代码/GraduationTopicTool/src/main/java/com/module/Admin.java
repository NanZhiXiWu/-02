package com.module;

public class Admin extends User{
    private String gender;
    private String phone;
    private String email;

    public Admin() {
    }

    public Admin(String id, String username, String password, String role, String gender, String phone, String email) {
        super(id, username, password, role);
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
