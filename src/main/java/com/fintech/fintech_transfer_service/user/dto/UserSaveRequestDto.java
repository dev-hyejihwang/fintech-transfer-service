package com.fintech.fintech_transfer_service.user.dto;

public class UserSaveRequestDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public UserSaveRequestDto() {
    }

    public UserSaveRequestDto(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
}
