package com.example.lab2;

public class analytics {
    private int ID;
    private String COMP_NAME;
    private int CLIENTS;
    private double MONEY;
    private double investments_return;

    public analytics(int ID, String COMP_NAME, int CLIENTS, double MONEY, double investments_return){
        this.ID = ID;
        this.COMP_NAME = COMP_NAME;
        this.CLIENTS = CLIENTS;
        this.MONEY = MONEY;
        this.investments_return = investments_return;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCOMP_NAME() {
        return COMP_NAME;
    }

    public void setCOMP_NAME(String COMP_NAME) {
        this.COMP_NAME = COMP_NAME;
    }

    public int getCLIENTS() {
        return CLIENTS;
    }

    public void setCLIENTS(int CLIENTS) {
        this.CLIENTS = CLIENTS;
    }

    public double getMONEY() {
        return MONEY;
    }

    public void setMONEY(double MONEY) {
        this.MONEY = MONEY;
    }

    public double getInvestments_return() {
        return investments_return;
    }

    public void setInvestments_return(double investments_return) {
        this.investments_return = investments_return;
    }

    @Override
    public String toString(){
        return ("AnalyticsCompany: {" +
                "\nID: " + ID +
                "\nName: " + COMP_NAME +
                "\nCLients: " +CLIENTS +
                "\nMoney: " +MONEY +
                "\nInvestmentsMoney: " +investments_return +
                "}");
    }
}
