package com.taxi.controller;

import com.taxi.dao.*;
import com.taxi.dao.daoImpl.*;
import com.taxi.domain.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    UserDao userDao = new UserDaoImpl();
    StreetDao streetDao = new StreetDaoImpl();
    TaxiDao taxiDao = new TaxiDaoImpl();
    WayDao wayDao = new WayDaoImpl();
    BookingDao bookingDao = new BookingDaoImpl();
    ActionDao actionDao = new ActionDaoImpl();
    UserActionDao userActionDao = new UserActionDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Street> streets = streetDao.getAllStreets();
        req.setAttribute("streets", streets);

        String userName = req.getParameter("userName");
        req.setAttribute("userName", userName);
        System.out.println("username booking: " + userName);

        List<Taxi> taxis = taxiDao.getAllAvailableCars();
        req.setAttribute("taxis", taxis);

        HttpSession session = req.getSession();
        String usName = session.getAttribute("user").toString();

        User user = new User();
        user.setUserId(userDao.getIdByUserName(usName));
        user.setUserName(usName);

        List<Booking> bookingList = bookingDao.getAllBookings(user);
        req.setAttribute("bookingList", bookingList);

        RequestDispatcher rd = req.getRequestDispatcher("/view/bookingtaxi.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Street> streets = streetDao.getAllStreets();
        req.setAttribute("streets", streets);

        List<Taxi> taxis = taxiDao.getAllAvailableCars();
        req.setAttribute("taxis", taxis);

        //userName isn't working here
        String userName = req.getParameter("userName");
        req.setAttribute("userName", userName);
        System.out.println("username booking: " + userName);

        HttpSession session = req.getSession();
        String usName = session.getAttribute("user").toString();

        User user = new User();
        user.setUserId(userDao.getIdByUserName(usName));
        user.setUserName(usName);


        String home = req.getParameter("home");
        String dest = req.getParameter("dest");
        //TODO if there are no available cars don't allow booking
        String car = req.getParameter("carchoose");
//        if(car.length() != 0){
//        work here booking
//        }
        //Get available car
        Taxi taxi = taxiDao.getCarByCarType(car);
        taxi.toString();
        //Get streets home and dest
        Street homeStreet = new Street(home);
        homeStreet.setId(streetDao.getStreetIdByName(home));
        Street destStreet = new Street(dest);
        destStreet.setId(streetDao.getStreetIdByName(dest));

        System.out.println(home);
        System.out.println(dest);
        System.out.println(car);
        System.out.println("home street id = " + homeStreet.toString());
        System.out.println("dest street id = " + destStreet.toString());
        //get sum km of way
        double km = wayDao.getSumKm(homeStreet,destStreet);

        System.out.println(km + "km");
        System.out.println("USER DETAILS: " + user.toString());
        //TODO (sum of km is calculated right,
        // need to calculate the price and wait
        // and book booking
        // and set taxi to not free and after some time to free
        // add Action for user
        Action action = actionDao.getUserAction(user);
        UserAction userAction = userActionDao.getUserActionByAction(action);
        //get user discount

        //working here
        //get id's streets and sum(km) and price and maybe coef and time arrival
        //book a taxi and after some time of booking set isFree taxi = true;
        //Get price
        double price = km * 11;
        if(action.getDiscount() > 20){
            price = price- action.getDiscount();
        }else{
            actionDao.addSumToAction(user, action, price%10);
        }
        System.out.println("price before discount is: " + price);
       // price = km * 11 - action.getDiscount();
        System.out.println("PRICE AFTER DISCOUNT: " + price);
        System.out.println("Taxi Details: " + taxi.toString());
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setHome(homeStreet);
        booking.setDest(destStreet);
        booking.setTaxi(taxi);
        //TODO action handling
        //TODO taxi free notFree handling
        //TODO make thread for executing taxi free function
        booking.setAction(userAction);
        booking.setPrice(price);
        //TODO calculate wait time
       // bookingDao.book(booking);
       // taxi.setIs_free(false);
       // taxiDao.changeCurrentPos(taxi, destStreet);
        //TODO set taxi to free after some time in thread


        RequestDispatcher rd = req.getRequestDispatcher("/view/bookingtaxi.jsp");
        rd.forward(req, resp);
    }
}
