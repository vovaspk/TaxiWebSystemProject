package com.taxi.controller;

import com.taxi.dao.ActionDao;
import com.taxi.dao.UserActionDao;
import com.taxi.dao.UserDao;
import com.taxi.dao.daoImpl.ActionDaoImpl;
import com.taxi.dao.daoImpl.UserActionDaoImpl;
import com.taxi.dao.daoImpl.UserDaoImpl;
import com.taxi.domain.Action;
import com.taxi.domain.User;
import com.taxi.domain.UserAction;
import com.taxi.passwordHashingService.MD5;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    Logger log = Logger.getLogger(RegistrationServlet.class);
    UserDaoImpl userDao = new UserDaoImpl();
    ActionDao actionDao = new ActionDaoImpl();
    UserActionDao userActionDao = new UserActionDaoImpl();
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
            log.warn("Cannot register, User already exists");
        }else{
            //save user in db
            userDao.save(user);
            //create action for him
            Action action = new Action();
            //save action
            actionDao.addNewAction(action);
            //create userAction
            UserAction userAction = new UserAction();
            //save userAction
            userActionDao.createnewUserAction(action);
            log.info("User registred successfully");

            RequestDispatcher rd = req.getRequestDispatcher("/view/login.jsp");
            rd.forward(req,resp );
            //resp.sendRedirect("/login");
        }

        System.out.println("RegistrationServlet doPost userName: " + uname);
        System.out.println("RegistrationServlet doPost mail: " + mail);
        System.out.println("RegistrationServlet doPost password: " + pass);
    }
}
