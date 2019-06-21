package com.taxi.dao.daoImpl;

import com.taxi.DBUtil.ConnectionFactory;
import com.taxi.dao.WayDao;
import com.taxi.domain.Street;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WayDaoImpl implements WayDao {
    public double getSumKm(Street home, Street dest) {
        double  km = 0;
        int startId = home.getId();
        int destId = dest.getId()-1;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT start_id, dest_id, sum(km)\n" +
                    "from ways\n" +
                    "    join streets s on ways.start_id = s.id\n" +
                    "    join streets s1 on ways.dest_id=s1.id\n" +
                    "where start_id between ? and ?;");
            stmt.setInt(1, startId);
            stmt.setInt(2, destId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                km = rs.getDouble(3);
            }
            return km;
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

        return 0;
    }
}
