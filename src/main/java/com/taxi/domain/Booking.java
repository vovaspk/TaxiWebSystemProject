package com.taxi.domain;

public class Booking {
    private int id;
    private Street home;
    private Street dest;
    private Taxi taxi;
    private UserAction action;
    private double price;

    public Booking() {
    }

    public Booking(Street home, Street dest, Taxi taxi, UserAction action, double price) {
        this.home = home;
        this.dest = dest;
        this.taxi = taxi;
        this.action = action;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Street getHome() {
        return home;
    }

    public void setHome(Street home) {
        this.home = home;
    }

    public Street getDest() {
        return dest;
    }

    public void setDest(Street dest) {
        this.dest = dest;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public UserAction getAction() {
        return action;
    }

    public void setAction(UserAction action) {
        this.action = action;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
