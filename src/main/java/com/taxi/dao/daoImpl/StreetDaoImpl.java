package com.taxi.dao.daoImpl;

import com.taxi.DBUtil.ConnectionFactory;
import com.taxi.dao.StreetDao;
import com.taxi.domain.Street;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StreetDaoImpl implements StreetDao {

    public Street getById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM streets WHERE id=?");
            rs = stmt.executeQuery();
            stmt.setInt(1, id);
            Street street = null;
            if (rs.next()) {

                street.setId(rs.getInt(1));
                street.setName(rs.getString(2));


            }
            return street;
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

    public int getStreetIdByName(String name) {
        int id = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM streets WHERE streetName=?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
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
        return id;
    }

    public List<Street> getAllStreets() {
        List<Street> streets = new ArrayList<Street>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM streets");
            rs = stmt.executeQuery();
            Street street = new Street();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Street st = new Street(name);
                st.setId(id);
//                street.setId(rs.getInt(1));
//                street.setName(rs.getString(2));
                streets.add(st);

            }
            return streets;
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

    public void save(Street street) {

    }

    public void update(Street street) {

    }

    public void delete(Street street) {

    }
}
