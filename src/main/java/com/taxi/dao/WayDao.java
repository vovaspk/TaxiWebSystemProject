package com.taxi.dao;

import com.taxi.domain.Street;

public interface WayDao {
    double getSumKm(Street home, Street dest);
}
