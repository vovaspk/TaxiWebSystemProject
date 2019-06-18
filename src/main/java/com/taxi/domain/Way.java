package com.taxi.domain;

public class Way {
    private int id;
    private Street home;
    private Street dest;
    private double km;
    private double coef;

    public Way() {
    }

    public Way(Street home, Street dest, double km, double coef) {
        this.home = home;
        this.dest = dest;
        this.km = km;
        this.coef = coef;
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

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }
}
