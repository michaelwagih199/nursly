package com.polimigo.babydaycare.model;


import com.google.firebase.firestore.DocumentId;

public class SeekerBookingModel {
    @DocumentId
    private String documentId;
    private String name;
    private String phone;
    private String numberBab;
    private String notes;
    private String nurslyName;

    public SeekerBookingModel(String documentId, String name, String phone, String numberBab, String notes, String nurslyName) {
        this.documentId = documentId;
        this.name = name;
        this.phone = phone;
        this.numberBab = numberBab;
        this.notes = notes;
        this.nurslyName = nurslyName;
    }

    public SeekerBookingModel() {
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getNumberBab() {
        return numberBab;
    }

    public void setNumberBab(String numberBab) {
        this.numberBab = numberBab;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNurslyName() {
        return nurslyName;
    }

    public void setNurslyName(String nurslyName) {
        this.nurslyName = nurslyName;
    }
}
