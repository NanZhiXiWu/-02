package com.module;

public class Dean  extends User{
    private String gender;
    private String phone;
    private String email;
    private String departmentName;

    public Dean() {
    }

    public Dean(String id, String username, String password, String role, String gender, String phone, String email, String departmentName) {
        super(id, username, password, role);
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.departmentName = departmentName;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
