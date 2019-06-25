package com.taxi.dao;

import com.taxi.domain.Booking;
import com.taxi.domain.User;

import java.util.List;

public interface BookingDao {
    public void book(Booking booking);
    public void cancelBook(Booking booking);
    public List<Booking> getAllBookings(User user);
    public Booking getBooking(int id);
}
