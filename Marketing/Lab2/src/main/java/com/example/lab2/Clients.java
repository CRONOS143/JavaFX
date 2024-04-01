package com.example.lab2;

public class Clients {
    private int id;
    private String name;
    private String ownership_type;
    private String address;
    private String phone;

    public Clients(int id, String name, String ownership_type, String address, String phone){
        this.id = id;
        this.name = name;
        this.ownership_type = ownership_type;
        this.address = address;
        this.phone = phone;
    }

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

    public String getOwnership_type() {
        return ownership_type;
    }

    public void setOwnership_type(String ownership_type) {
        this.ownership_type = ownership_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return ("Clients:"+
                "\nID: " + id +
                "\nName = " + name +
                "\nOwnership_type = " + ownership_type +
                "\nAddress = " + address +
                "\nPhone = " + phone +
                "\n");
    }
}
