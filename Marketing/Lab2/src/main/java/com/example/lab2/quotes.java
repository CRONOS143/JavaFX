package com.example.lab2;

import javafx.fxml.FXML;

import java.sql.Date;

public class quotes {
    private int id;
    private String security_name;
    private double price;
    private Date date;
    private int DOVIRA;

    public quotes(int id, String security_name, double price, Date date, int Dovira){
        this.id = id;
        this.security_name = security_name;
        this.price = price;
        this.date = date;
        this.DOVIRA = Dovira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecurity_name() {
        return security_name;
    }

    public void setSecurity_name(String security_name) {
        this.security_name = security_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDOVIRA() {
        return DOVIRA;
    }

    public void setDOVIRA(int DOVIRA) {
        this.DOVIRA = DOVIRA;
    }


    @Override
    public String toString(){
        return("Quotes: {" +
               "\nID: " + id +
               "\nSecurity_id: " + security_name +
               "\nPrice: " + price +
               "\nDate: " + date +
               "\nDovira: " + DOVIRA +
                "\n}");
    }
}
