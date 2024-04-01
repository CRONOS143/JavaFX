package com.example.projectcoursework;

import java.time.LocalDate;

public class VLASNIK {
    private int id;
    private String name;
    private String surname;
    private String middleName;
    private long mobilePhone;
    private LocalDate dateBirthday;

    // Конструктор
    public VLASNIK(int id, String name, String surname, String middleName, long mobilePhone, LocalDate dateBirthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.mobilePhone = mobilePhone;
        this.dateBirthday = dateBirthday;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public long getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(long mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public LocalDate getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(LocalDate dateBirthday) {
        this.dateBirthday = dateBirthday;
    }
}
