package com.example.projectcoursework;

import java.time.LocalDate;

public class Auto {
    private int id;
    private String model;
    private String number;
    private LocalDate Date_Of_Made;
    private String name_1;
    private String surname_1;
    private String middleName_1;

    // Конструктор
    public Auto(int id, String model, String number, LocalDate dateOfMade, String name, String surname, String middleName) {
        this.id = id;
        this.model = model;
        this.number = number;
        this.Date_Of_Made = dateOfMade;
        this.name_1 = name;
        this.surname_1 = surname;
        this.middleName_1 = middleName;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate_Of_Made() {
        return Date_Of_Made;
    }

    public void setDate_Of_Made(LocalDate dateOfMade) {
        this.Date_Of_Made = dateOfMade;
    }

    public String getName_1() {
        return name_1;
    }

    public void setName_1(String name) {
        this.name_1 = name;
    }

    public String getSurname_1() {
        return surname_1;
    }

    public void setSurname_1(String surname_1) {
        this.surname_1 = surname_1;
    }

    public String getMiddleName_1() {
        return middleName_1;
    }

    public void setMiddleName_1(String middleName_1) {
        this.middleName_1 = middleName_1;
    }
}

