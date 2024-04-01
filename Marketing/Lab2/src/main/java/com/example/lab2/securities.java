package com.example.lab2;

public class securities {
    private int id;
    private String name;
    private String type;

    public securities(int id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return ("Securities:" +
                "\nID: " + id +
                "\nName = " + name +
                "\nType = " + type +
                "\n");
    }
}
