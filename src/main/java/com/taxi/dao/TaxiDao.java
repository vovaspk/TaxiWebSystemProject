package com.taxi.dao;

import com.taxi.domain.Street;
import com.taxi.domain.Taxi;

import java.util.List;

public interface TaxiDao {
    List<Taxi> getAllCars();
    List<Taxi> getAllAvailableCars();
    Taxi getCarByCarType(String carType);
    Taxi getCarById(int id);
    void setCarBusy(Taxi taxi);
    void setCarFree(Taxi taxi);
    void changeCurrentPos(Taxi taxi, Street street);
}
