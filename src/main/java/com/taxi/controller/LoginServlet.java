package com.taxi.controller;

import com.taxi.dao.daoImpl.UserDaoImpl;
import com.taxi.passwordHashingService.MD5;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
   private UserDaoImpl userDaoImpl = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

         RequestDispatcher rd = req.getRequestDispatcher("/view/login.jsp");
        rd.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //This receives when come from registration page
//        RequestDispatcher rd = req.getRequestDispatcher("/view/login.jsp");
//        rd.forward(req, resp);




        String userName = req.getParameter("userName");
        System.out.println("LoginServletDoPost username: " + userName);
        String password = req.getParameter("password");
        System.out.println("LoginServletDoPost password: " + password);
        String hashedPassword = MD5.getMD5(password);
        System.out.println("hashedPassword LoginServletDoPost:" + hashedPassword);
        //System.out.println("LoginServlet: " + userName);
        req.setAttribute("userName", userName);
        req.setAttribute("password", hashedPassword);
        String pasred = "'" + hashedPassword + "'";
        if(userDaoImpl.getUserByUserNameAndPassword(userName, hashedPassword)){
            System.out.println("Login success");
            HttpSession session = req.getSession();
            session.setAttribute("user", userName);
            RequestDispatcher rd = req.getRequestDispatcher("/view/home.jsp");
            rd.forward(req, resp);
        }else{
            System.out.println("login wrong");
            resp.sendRedirect("/view/login.jsp");
        }
    }
}
