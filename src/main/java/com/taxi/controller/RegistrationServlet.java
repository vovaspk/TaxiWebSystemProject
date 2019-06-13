package com.taxi.controller;

import com.taxi.dao.UserDao;
import com.taxi.dao.daoImpl.UserDaoImpl;
import com.taxi.domain.User;
import com.taxi.passwordHashingService.MD5;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    UserDaoImpl userDao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/view/registration.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher rd = req.getRequestDispatcher("/view/registration.jsp");
//        rd.forward(req, resp);

        String uname = req.getParameter("userName");
        String mail = req.getParameter("mail");
        String pass =req.getParameter("password");
        String hashedPassword = MD5.getMD5(pass);
        System.out.println("RegistrationServlet");
        User user = new User(uname, mail, hashedPassword);
        if(userDao.getUserByUserNameAndPassword(uname, hashedPassword)){
            System.out.println("User already exists!");
        }else{
            userDao.save(user);
            RequestDispatcher rd = req.getRequestDispatcher("/view/login.jsp");
            rd.forward(req,resp );
            //resp.sendRedirect("/login");
        }

        System.out.println("RegistrationServlet doPost userName: " + uname);
        System.out.println("RegistrationServlet doPost mail: " + mail);
        System.out.println("RegistrationServlet doPost password: " + pass);
    }
}
