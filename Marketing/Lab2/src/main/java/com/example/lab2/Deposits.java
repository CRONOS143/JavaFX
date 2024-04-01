package com.example.lab2;

import java.sql.Date;

public class Deposits {
    private int id;
    private double amount;
    private int DOVIRA;
    private Date date;

    public Deposits(int id, double amount, int Dovira, Date date) {
        this.id = id;
        this.amount = amount;
        this.DOVIRA = Dovira;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDOVIRA() {
        return DOVIRA;
    }

    public void setDOVIRA(int DOVIRA) {
        this.DOVIRA = DOVIRA;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return ("Deposits:" +
                "\nID: " + id +
                "\nAmount = " + amount +
                "\nDovira: " + DOVIRA +
                "\nDate = " + date +
                "\n");
    }
}