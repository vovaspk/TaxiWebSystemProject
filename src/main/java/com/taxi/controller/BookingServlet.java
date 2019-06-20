package com.taxi.controller;

import com.taxi.dao.StreetDao;
import com.taxi.dao.TaxiDao;
import com.taxi.dao.daoImpl.StreetDaoImpl;
import com.taxi.dao.daoImpl.TaxiDaoImpl;
import com.taxi.domain.Street;
import com.taxi.domain.Taxi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    StreetDao streetDao = new StreetDaoImpl();
    TaxiDao taxiDao = new TaxiDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Street> streets = streetDao.getAllStreets();
        req.setAttribute("streets", streets);

        List<Taxi> taxis = taxiDao.getAllAvailableCars();
        req.setAttribute("taxis", taxis);

        RequestDispatcher rd = req.getRequestDispatcher("/view/bookingtaxi.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Street> streets = streetDao.getAllStreets();
        req.setAttribute("streets", streets);

        List<Taxi> taxis = taxiDao.getAllAvailableCars();
        req.setAttribute("taxis", taxis);

        String home = req.getParameter("home");
        String dest = req.getParameter("dest");
        String car = req.getParameter("carchoose");
        System.out.println(home);
        System.out.println(dest);
        System.out.println(car);
        //working here
        //get id's streets and sum(km) and price and maybe coef and time arrival
        //book a taxi and after some time of booking set isFree taxi = true;

        RequestDispatcher rd = req.getRequestDispatcher("/view/bookingtaxi.jsp");
        rd.forward(req, resp);
    }
}
