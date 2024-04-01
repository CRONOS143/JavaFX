package com.example.lab2;

import java.sql.Date;

public class Dovira {
    private int id;
    private Date term_start;
    private Date term_end;
    private String Fund;
    private String name;
    private double investment_amount;
    private String COMP_NAME;
    private double investments_return;

    public Dovira(int id, Date term_start, Date term_end, String Fund, String name,
                  double investment_amount, String COMP_NAME, double investments_return){
        this.id = id;
        this.term_start = term_start;
        this.term_end = term_end;
        this.Fund = Fund;
        this.name = name;
        this.investment_amount = investment_amount;
        this.COMP_NAME = COMP_NAME;
        this.investments_return = investments_return;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTerm_start() {
        return term_start;
    }

    public void setTerm_start(Date term_start) {
        this.term_start = term_start;
    }

    public Date getTerm_end() {
        return term_end;
    }

    public void setTerm_end(Date term_end) {
        this.term_end = term_end;
    }

    public String getFund() {
        return Fund;
    }

    public void setFund(String fund) {
        Fund = fund;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInvestment_amount() {
        return investment_amount;
    }

    public void setInvestment_amount(double investment_amount) {
        this.investment_amount = investment_amount;
    }

    public String getCOMP_NAME() {
        return COMP_NAME;
    }

    public void setCOMP_NAME(String COMP_NAME) {
        this.COMP_NAME = COMP_NAME;
    }

    public double getInvestments_return() {
        return investments_return;
    }

    public void setInvestments_return(double investments_return) {
        this.investments_return = investments_return;
    }
    @Override
    public String toString(){
        return("Dovira: {" +
                "\nID: " + id +
                "\nterm_start: " + term_start +
                "\nterm_end: " + term_end +
                "\nFund: " + Fund +
                "\nName: " + name +
                "\ninvestment_amount: " + investment_amount +
                "\nCOMP_NAME: " + COMP_NAME +
                "\ninvestments_return: " + investments_return +
                "}");
    }
}
