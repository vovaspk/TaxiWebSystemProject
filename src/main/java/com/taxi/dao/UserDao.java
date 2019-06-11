package com.taxi.dao;

import com.taxi.domain.User;

import java.util.List;

public interface UserDao {
    User getUserById(long id);
    List<User> getAllUsers();
    void save(User user);
    void update(User user);
    void delete(User user);

}
