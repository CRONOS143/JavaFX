package com.example.projectcoursework;

import java.time.LocalDate;

public class Ordered {
    private int id;
    private String model;
    private String number;
    private LocalDate dateOfMade;
    private String name;
    private String surname;
    private String middleName;
    private double cost;
    private LocalDate dateOfIssue;
    private String typeOfWork;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String executor;
    private int experienceExecutor;

    // Конструктор
    public Ordered(int id, String model, String number, LocalDate dateOfMade, String name, String surname, String middleName,
                   double cost, LocalDate dateOfIssue, String typeOfWork, LocalDate dateStart, LocalDate dateEnd,
                   String executor, int experienceExecutor) {
        this.id = id;
        this.model = model;
        this.number = number;
        this.dateOfMade = dateOfMade;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.cost = cost;
        this.dateOfIssue = dateOfIssue;
        this.typeOfWork = typeOfWork;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.executor = executor;
        this.experienceExecutor = experienceExecutor;
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

    public LocalDate getDateOfMade() {
        return dateOfMade;
    }

    public void setDateOfMade(LocalDate dateOfMade) {
        this.dateOfMade = dateOfMade;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public int getExperienceExecutor() {
        return experienceExecutor;
    }

    public void setExperienceExecutor(int experienceExecutor) {
        this.experienceExecutor = experienceExecutor;
    }
}
