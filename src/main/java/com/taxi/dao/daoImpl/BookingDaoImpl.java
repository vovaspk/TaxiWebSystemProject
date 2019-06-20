package com.taxi.dao.daoImpl;

import com.taxi.DBUtil.ConnectionFactory;
import com.taxi.dao.BookingDao;
import com.taxi.domain.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    private final int priceFor1km = 6;
    public void book(Booking booking) {
        long userId = booking.getUser().getUserId();
        int home = booking.getHome().getId();
        int dest = booking.getDest().getId();
        int car = booking.getTaxi().getId();
        int action = booking.getAction().getId();
        //calculate price = coef(загруз дороги) km * price for 1 km + (- discount)
        double price = 5;

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
        }catch (SQLException e){

        }
        finally {
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
    }

    public void cancelBook(Booking booking) {

    }

    public List<Booking> getAllBookings() {
        return null;
    }

    public Booking getBooking(int id) {
        return null;
    }
}
