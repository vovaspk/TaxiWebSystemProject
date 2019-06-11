package com.taxi.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher rd = req.getRequestDispatcher("/view/home.jsp");
//        rd.forward(req, resp);

        String userName = req.getParameter("userName");
        System.out.println("HomeServlet: doGet" + userName);

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
