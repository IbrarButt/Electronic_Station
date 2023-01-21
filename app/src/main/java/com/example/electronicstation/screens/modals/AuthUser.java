package com.example.electronicstation.screens.modals;

public class AuthUser {
    private String userName;
    private  String userPassword;
    private  String mobileNumber;
    private  String userEmail;
    private Boolean isOwner;

    public AuthUser(String userName, String userPassword, String mobileNumber, String userEmail, Boolean isOwner) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.mobileNumber = mobileNumber;
        this.userEmail = userEmail;
        this.isOwner = isOwner;
    }
    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner(Boolean owner) {
        isOwner = owner;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
