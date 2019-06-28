package com.taxi.controller;

import com.taxi.dao.*;
import com.taxi.dao.daoImpl.*;
import com.taxi.domain.*;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
   Logger log = Logger.getLogger(BookingServlet.class);
   private final double timeFor1KM = 2.5;
   private final double priceFor1KM = 11;
   private final double minDiscountValue= 20;
   private UserDao userDao = new UserDaoImpl();
   private StreetDao streetDao = new StreetDaoImpl();
   private TaxiDao taxiDao = new TaxiDaoImpl();
   private WayDao wayDao = new WayDaoImpl();
   private BookingDao bookingDao = new BookingDaoImpl();
   private ActionDao actionDao = new ActionDaoImpl();
   private UserActionDao userActionDao = new UserActionDaoImpl();



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
        //get right userAction
        UserAction userAction = userActionDao.getUserActionByAction(action);
        System.out.println(userAction.toString());
        //get user discount

        //working here
        //get id's streets and sum(km) and price and maybe coef and time arrival
        //book a taxi and after some time of booking set isFree taxi = true;
        //Get price
        double price = km * priceFor1KM;
        if(action.getDiscount() > minDiscountValue){
            price = price- action.getDiscount();
        }else{
            actionDao.addSumToAction(user, action, price%10);
        }
        System.out.println("price before discount is: " + price);
       // price = km * 11 - action.getDiscount();
        System.out.println("PRICE AFTER DISCOUNT: " + price);
        System.out.println("Taxi Details: " + taxi.toString());
        //WAITING TIME
        double waitTime = wayDao.getSumKm(homeStreet, taxi.getCurr_pos()) * timeFor1KM;
        int time = (int) waitTime;

        req.setAttribute("waitingTime", time);
        System.out.println("Wait time for your car is: " + waitTime + " min");
        //Booking handling
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setHome(homeStreet);
        booking.setDest(destStreet);
        booking.setTaxi(taxi);
        booking.setAction(userAction);
        booking.setPrice(price);
        bookingDao.book(booking);
        //set car is not free, change car position, in time set car to free
        taxi.setIs_free(false);
        taxiDao.setCarBusy(taxi);
        taxiDao.changeCurrentPos(taxi, destStreet);
        //taxiDao.setCarFree(taxi);


        List<Booking> bookingList = bookingDao.getAllBookings(user);
        req.setAttribute("bookingList", bookingList);

        RequestDispatcher rd = req.getRequestDispatcher("/view/bookingtaxi.jsp");
        rd.forward(req, resp);
    }
}
