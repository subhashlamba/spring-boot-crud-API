package com.prominentpixel.angularexcercise.dto;

public class UserDTO {

    public UserDTO() {
    }

    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String mobile;

    private String status;

    private Boolean newCompany;

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Boolean getNewCompany() {
        return newCompany;
    }

    public void setNewCompany(Boolean newCompany) {
        this.newCompany = newCompany;
    }
}
