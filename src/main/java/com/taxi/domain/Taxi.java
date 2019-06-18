package com.taxi.domain;

public class Taxi {
    private int id;
    private String carClass;
    private Street curr_pos;
    private boolean is_free;

    public Taxi() {
    }

    public Taxi(String carClass, Street curr_pos, boolean is_free) {
        this.carClass = carClass;
        this.curr_pos = curr_pos;
        this.is_free = is_free;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public Street getCurr_pos() {
        return curr_pos;
    }

    public void setCurr_pos(Street curr_pos) {
        this.curr_pos = curr_pos;
    }

    public boolean isIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }
}
