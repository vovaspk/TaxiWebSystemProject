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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    StreetDao streetDao = new StreetDaoImpl();
    TaxiDao taxiDao = new TaxiDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher rd = req.getRequestDispatcher("/view/home.jsp");
//        rd.forward(req, resp);

        String userName = req.getParameter("userName");
        System.out.println("HomeServlet: doGet" + userName);
        req.setAttribute("userName", userName);

        List<Street> streets = streetDao.getAllStreets();
        req.setAttribute("streets", streets);

        List<Taxi> taxis = taxiDao.getAllAvailableCars();
        req.setAttribute("taxis", taxis);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/view/home.jsp");
        rd.forward(req, resp);

        String userName = req.getParameter("userName");
        System.out.println("HomeServlet: doPost " + userName);
        String password = req.getParameter("password");
        System.out.println("HomeServlet: doPost " + password);
    }
}
