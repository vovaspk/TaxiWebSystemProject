package com.taxi.domain;

public class Street {
    private int id;
    private String name;

    public Street() {
    }

    public Street(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
