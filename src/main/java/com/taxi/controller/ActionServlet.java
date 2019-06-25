package com.taxi.controller;

import com.taxi.dao.ActionDao;
import com.taxi.dao.UserDao;
import com.taxi.dao.daoImpl.ActionDaoImpl;
import com.taxi.dao.daoImpl.UserDaoImpl;
import com.taxi.domain.Action;
import com.taxi.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/actions")
public class ActionServlet extends HttpServlet {
    ActionDao actionDao = new ActionDaoImpl();
    UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get user and his action
        HttpSession session = req.getSession();
        String userName = session.getAttribute("user").toString();
        User user = new User();
        user.setUserName(userName);
        user.setUserId(userDao.getIdByUserName(userName));
        Action action = actionDao.getUserAction(user);
        req.setAttribute("action", action.getDiscount());

        RequestDispatcher rd = req.getRequestDispatcher("/view/actionForUser.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
