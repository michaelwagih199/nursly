package com.polimigo.babydaycare.model;

import com.google.firebase.firestore.DocumentId;

public class NurslyModel {
    @DocumentId
    private String documentId;
    private String userName;
    private String nurslyName;
    private String nurslyPhone;
    private String nurslyBedsNumber;
    private String governorate;
    private String region;
    private String address;
    private String Latitude;
    private String Longitude;


    public NurslyModel() {
    }

    public NurslyModel(String documentId, String nurslyName, String nurslyPhone, String nurslyBedsNumber, String governorate, String region, String address, String latitude, String longitude,String UserName) {
        this.documentId = documentId;
        this.nurslyName = nurslyName;
        this.nurslyPhone = nurslyPhone;
        this.nurslyBedsNumber = nurslyBedsNumber;
        this.governorate = governorate;
        this.region = region;
        this.address = address;
        Latitude = latitude;
        Longitude = longitude;
        this.userName = userName;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getNurslyName() {
        return nurslyName;
    }

    public void setNurslyName(String nurslyName) {
        this.nurslyName = nurslyName;
    }

    public String getNurslyPhone() {
        return nurslyPhone;
    }

    public void setNurslyPhone(String nurslyPhone) {
        this.nurslyPhone = nurslyPhone;
    }

    public String getNurslyBedsNumber() {
        return nurslyBedsNumber;
    }

    public void setNurslyBedsNumber(String nurslyBedsNumber) {
        this.nurslyBedsNumber = nurslyBedsNumber;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "NurslyModel{" +
                "documentId='" + documentId + '\'' +
                ", userName='" + userName + '\'' +
                ", nurslyName='" + nurslyName + '\'' +
                ", nurslyPhone='" + nurslyPhone + '\'' +
                ", nurslyBedsNumber='" + nurslyBedsNumber + '\'' +
                ", governorate='" + governorate + '\'' +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", Latitude='" + Latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                '}';
    }
}
