package com.taxi.dao;

import com.taxi.domain.Street;

import java.util.List;

public interface StreetDao {
    Street getById(int id);
    List<Street> getAllStreets();
    void save (Street street);
    void update (Street street);
    void delete (Street street);
}
