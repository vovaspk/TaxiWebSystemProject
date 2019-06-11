package com.taxi.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/view/registration.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/view/registration.jsp");
        rd.forward(req, resp);

        String uname = req.getParameter("userName");
        String mail = req.getParameter("password");
        String pass = req.getParameter("password");
        System.out.println("RegistrationServlet doPost userName: " + uname);
        System.out.println("RegistrationServlet doPost mail: " + mail);
        System.out.println("RegistrationServlet doPost password: " + pass);
    }
}
