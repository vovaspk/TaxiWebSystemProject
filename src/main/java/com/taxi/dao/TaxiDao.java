package com.taxi.dao;

import com.taxi.domain.Taxi;

import java.util.List;

public interface TaxiDao {
    List<Taxi> getAllCars();
    List<Taxi> getAllAvailableCars();
    Taxi getCarByCarType(String carType);
}
