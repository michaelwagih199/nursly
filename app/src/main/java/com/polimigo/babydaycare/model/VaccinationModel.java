package com.polimigo.babydaycare.model;

import com.google.firebase.firestore.DocumentId;

public class VaccinationModel {
    @DocumentId
    private String documentId;
    private String vacationName;
    private String vacationDate;
    private String age;

    public VaccinationModel(String documentId, String vacationName, String vacationDate, String age) {
        this.documentId = documentId;
        this.vacationName = vacationName;
        this.vacationDate = vacationDate;
        this.age = age;
    }

    public VaccinationModel() {
    }

    public String getVacationName() {
        return vacationName;
    }

    public void setVacationName(String vacationName) {
        this.vacationName = vacationName;
    }

    public String getVacationDate() {
        return vacationDate;
    }

    public void setVacationDate(String vacationDate) {
        this.vacationDate = vacationDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
