package com.example.projectcoursework;

public class Mechanic {
    private int id;
    private String name;
    private String middleName;
    private String surname;
    private int experience;
    private String typeOfWork;
    private int age;

    // Конструктор
    public Mechanic(int id, String name, String middleName, String surname, int experience, String typeOfWork, int age) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.experience = experience;
        this.typeOfWork = typeOfWork;
        this.age = age;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
