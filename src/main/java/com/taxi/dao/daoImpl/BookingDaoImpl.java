package com.taxi.dao.daoImpl;

import com.taxi.DBUtil.ConnectionFactory;
import com.taxi.dao.*;
import com.taxi.domain.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    Logger log = Logger.getLogger(BookingDaoImpl.class);
  private StreetDao streetDao = new StreetDaoImpl();
  private ActionDao actionDao = new ActionDaoImpl();
  private TaxiDao taxiDao = new TaxiDaoImpl();
  private UserActionDao userActionDao = new UserActionDaoImpl();
   // private final int priceFor1km = 6;

    public void book(Booking booking) {
        long userId = booking.getUser().getUserId();
        int home = booking.getHome().getId();
        int dest = booking.getDest().getId();
        int car = booking.getTaxi().getId();
        int action = booking.getAction().getId();
        double price = booking.getPrice();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("INSERT INTO booking (userId, home, dest, action, car, price) VALUES (?,?,?,?,?,?)");
            stmt.setLong(1, userId);
            stmt.setInt(2, home);
            stmt.setInt(3, dest);
            stmt.setInt(4, action);
            stmt.setInt(5, car);
            stmt.setDouble(6, price);
            stmt.executeUpdate();
            System.out.println("user saved");
            log.info("BookingDaoImpl: booked successfully");
        }catch (SQLException e){
            e.getMessage();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    log.error("Cannot book" + e.getMessage());
                    e.getMessage();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error("Connection exception: " + e.getMessage());
                    e.getMessage();
                }
            }
        }
    }

    public void cancelBook(Booking booking) {

    }

    public List<Booking> getAllBookings(User user) {
        List<Booking> bookingList = new ArrayList<Booking>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM booking where userId=?");
            stmt.setInt(1, (int)user.getUserId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                int id = rs.getInt(1);
                int userId = rs.getInt(2);
                int home = rs.getInt(3);
                int dest = rs.getInt(4);
                Street homeStreet = streetDao.getById(home);
                Street destStreet = streetDao.getById(dest);
                int actionId = rs.getInt(5);
                Action action = actionDao.getUserAction(user);
                UserAction userAction = userActionDao.getUserActionByAction(action);
                int carId = rs.getInt(6);
                Taxi taxi = taxiDao.getCarById(carId);
                double price = rs.getDouble(7);
                //build booking
                booking.setId(id);
                booking.setUser(user);
                booking.setHome(homeStreet);
                booking.setDest(destStreet);
                booking.setAction(userAction);
                booking.setTaxi(taxi);
                booking.setPrice(price);

               bookingList.add(booking);

            }
            return bookingList;
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }

        return null;
    }

    public Booking getBooking(int id) {
        return null;
    }
}
