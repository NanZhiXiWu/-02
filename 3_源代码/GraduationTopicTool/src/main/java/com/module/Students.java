package com.module;

public class Students extends User{
    private String major;
    private String phone;

    public Students() {
    }

    public Students(String id, String username, String password, String role, String major, String phone) {
        super(id, username, password, role);
        this.major = major;
        this.phone = phone;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
