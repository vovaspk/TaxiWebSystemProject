package com.taxi.dao.daoImpl;

import com.taxi.DBUtil.ConnectionFactory;
import com.taxi.dao.ActionDao;
import com.taxi.domain.Action;
import com.taxi.domain.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionDaoImpl implements ActionDao{
    Logger log = Logger.getLogger(ActionDaoImpl.class);
    public void addNewAction(Action action) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("INSERT INTO action (discount) value ?");
            stmt.setDouble(1, 0);
            stmt.executeUpdate();
            log.info("action added successfully");

        } catch (SQLException e) {
            log.error("cannot add action: " + e.getMessage());
            e.getMessage();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    log.error("stmt error: " + e.getMessage());
                    e.getMessage();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error("connection error: " + e.getMessage());
                    e.getMessage();
                }
            }
        }
    }

    public void addSumToAction(User user, Action action, double sum) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("UPDATE action set discount=? where id=?");
            stmt.setDouble(1, sum);
            stmt.setLong(2, user.getUserId());
            rs = stmt.executeQuery();



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
    }

    public void takeSumFromAction(User user, Action action, double sum) {

    }

    public Action getUserAction(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT discount, userId " +
                    "from action join user_action ua on action.id = ua.action_id " +
                    "join users on ua.user_id = users.userId where ua.user_id = ?;");
            stmt.setLong(1, user.getUserId());
            rs = stmt.executeQuery();
            Action action = new Action();
            if (rs.next()) {
                action.setId(rs.getInt(2));
                action.setDiscount(rs.getInt(1));
            }
            return action;
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
