package com.taxi.dao.daoImpl;

import com.taxi.DBUtil.ConnectionFactory;
import com.taxi.dao.UserActionDao;
import com.taxi.dao.UserDao;
import com.taxi.domain.Action;
import com.taxi.domain.User;
import com.taxi.domain.UserAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserActionDaoImpl implements UserActionDao {
    UserDao userDao = new UserDaoImpl();

    public void createnewUserAction(Action action) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("INSERT INTO user_action (user_id, action_id) values ?, ?");
            stmt.setInt(1, action.getId());
            stmt.setInt(2, action.getId());
            stmt.executeUpdate();


        } catch (SQLException e) {
            e.getMessage();
        } finally {
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

    public UserAction getUserActionByAction(Action action) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM user_action WHERE action_id=?");
            stmt.setInt(1, action.getId());
            rs = stmt.executeQuery();
            User user;
            UserAction userAction = new UserAction();
            if (rs.next()) {
                userAction.setId(rs.getInt(1));
                user = userDao.getUserById(rs.getInt(3));
                userAction.setAction(action);
                userAction.setUser(user);
               // action.setDiscount(rs.getInt(1));
            }
            return userAction;
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
}
