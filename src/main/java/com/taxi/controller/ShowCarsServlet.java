package com.taxi.controller;

import com.taxi.dao.TaxiDao;
import com.taxi.dao.daoImpl.TaxiDaoImpl;
import com.taxi.domain.Taxi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cars")
public class ShowCarsServlet extends HttpServlet {

    TaxiDao taxiDao = new TaxiDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Taxi> cars = taxiDao.getAllCars();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
