package com.freedmore.myawesomeapp;

public class Country {
    private int id;
    private String name;
    private String currency;
    private double population;

    public Country() {
    }

    public Country(int id, String name, String currency, double population) {
        this.id=id;
        this.name = name;
        this.currency = currency;
        this.population = population;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }
}
