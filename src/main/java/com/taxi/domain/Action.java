package com.taxi.domain;

public class Action {
    private int id;
    private double discount;

    public Action() {
    }

    public Action(double discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", discount=" + discount +
                '}';
    }
}
