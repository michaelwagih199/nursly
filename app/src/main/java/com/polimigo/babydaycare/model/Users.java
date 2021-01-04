package com.polimigo.babydaycare.model;

import com.google.firebase.firestore.DocumentId;

public class Users {
    @DocumentId
    private String documentId;
    private String firstNameString;
    private String lastNameString;
    private String userName;
    private String password;
    private String userType;

    public Users() {

    }

    public Users(String firstNameString, String lastNameString, String userName, String password, String userType) {
        this.firstNameString = firstNameString;
        this.lastNameString = lastNameString;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }


    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getFirstNameString() {
        return firstNameString;
    }

    public void setFirstNameString(String firstNameString) {
        this.firstNameString = firstNameString;
    }

    public String getLastNameString() {
        return lastNameString;
    }

    public void setLastNameString(String lastNameString) {
        this.lastNameString = lastNameString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


}
