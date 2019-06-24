package com.taxi.dao.daoImpl;

import com.taxi.DBUtil.ConnectionFactory;
import com.taxi.dao.StreetDao;
import com.taxi.dao.TaxiDao;
import com.taxi.domain.Street;
import com.taxi.domain.Taxi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaxiDaoImpl implements TaxiDao {
    StreetDao streetDao = new StreetDaoImpl();

    public Taxi getCarByCarType(String carType) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM taxi WHERE class=?");
            stmt.setString(1, carType);
            rs = stmt.executeQuery();
           // Taxi taxi = new Taxi();
            if (rs.next()) {
                int id = rs.getInt(1);
                String taxiClass = rs.getString(2);
                Street curPos = new Street();
               int  streetId = rs.getInt(3);
                curPos.setId(rs.getInt(3));
                curPos.setName(streetDao.getById(streetId).getName());
                int is_free = rs.getInt(4);
                boolean isFree = is_free==1;
                Taxi tx = new Taxi();
                tx.setId(id);
                tx.setCarClass(taxiClass);
                tx.setCurr_pos(curPos);
                tx.setIs_free(isFree);
                return tx;


            }
            return null;
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

    public List<Taxi> getAllCars() {
        List<Taxi> taxis = new ArrayList<Taxi>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM taxi");
            rs = stmt.executeQuery();
            //Taxi taxi = new Taxi();
            while (rs.next()) {
                int id = rs.getInt(1);
                String taxiClass = rs.getString(2);
                Street curPos = new Street();
                curPos.setId(rs.getInt(3));
                curPos.setName(streetDao.getById(id).getName());
                int is_free = rs.getInt(4);
                boolean isFree = is_free==1;
                Taxi tx = new Taxi();
                tx.setId(id);
                tx.setCarClass(taxiClass);
                tx.setCurr_pos(curPos);
                tx.setIs_free(isFree);

                taxis.add(tx);

            }
            return taxis;
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

    public List<Taxi> getAllAvailableCars() {
            List<Taxi> taxis = new ArrayList<Taxi>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                conn = ConnectionFactory.getConnection();
                stmt = conn.prepareStatement("SELECT * FROM taxi WHERE is_free = 1");
                rs = stmt.executeQuery();
                //Taxi taxi = new Taxi();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String taxiClass = rs.getString(2);
                    Street curPos = streetDao.getById(rs.getInt(3)); //new Street();
                   // curPos.setId(rs.getInt(3));
                    //
                    //curPos.setName(streetDao.getById(id).getName());
                    //
                    int is_free = rs.getInt(4);
                    boolean isFree = is_free==1;
                    Taxi tx = new Taxi();
                    tx.setId(id);
                    tx.setCarClass(taxiClass);
                    tx.setCurr_pos(curPos);
                    tx.setIs_free(isFree);

                    taxis.add(tx);

                }
                return taxis;
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
