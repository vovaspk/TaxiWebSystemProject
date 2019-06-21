package com.taxi.dao.daoImpl;

import com.taxi.DBUtil.ConnectionFactory;
import com.taxi.dao.ActionDao;
import com.taxi.domain.Action;
import com.taxi.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionDaoImpl implements ActionDao
{
    public Action getUserAction(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT discount, userId " +
                    "from action join user_action ua on action.id = ua.action_id " +
                    "join users on ua.user_id = users.userId where ua.user_id = ?;");
            rs = stmt.executeQuery();
            stmt.setLong(1, user.getUserId());
            Action action = new Action();
            if (rs.next()) {
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
