package com.module;

public class Teacher extends User{
    private String code;
    private String gender;
    private String phone;
    private String departmentName;
    private String professionalTitle;
    private String detail;

    public Teacher() {
    }

    public Teacher(String id, String username, String password, String role, String code, String gender, String phone, String departmentName, String professionalTitle, String detail) {
        super(id, username, password, role);
        this.code = code;
        this.gender = gender;
        this.phone = phone;
        this.departmentName = departmentName;
        this.professionalTitle = professionalTitle;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
