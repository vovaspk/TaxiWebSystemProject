package com.taxi.dao.daoImpl;

import com.taxi.DBUtil.ConnectionFactory;
import com.taxi.dao.UserDao;
import com.taxi.domain.User;
import com.taxi.passwordHashingService.MD5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public int getIdByUserName(String name) {
        int id=0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT userId FROM users WHERE userName = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if(rs.next()) {
                    id = rs.getInt("userId");

            }
            return id;
        }catch (SQLException e){
            e.getMessage();
        }
        finally {
            if(rs !=null){
                try{
                    rs.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
            if(stmt != null){
                try{
                    stmt.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
        }


        return id;
    }
//ConnectionFactory dataSource;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM users");
            rs = stmt.executeQuery();
            //User user = null;
            if(rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserMail(rs.getString(3));
                user.setUserPassword(rs.getString(4));
                users.add(user);

               // users.add(processRow(rs));

            }
            return users;
        }catch (SQLException e){
            e.getMessage();
        }
        finally {
            if(rs !=null){
                try{
                    rs.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
            if(stmt != null){
                try{
                    stmt.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
        }

        return null;
    }

    public void save(User user) {
        String userName = user.getUserName();
        String email = user.getUserMail();
        //String password = MD5.getMD5(user.getUserPassword());
        String password = user.getUserPassword();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("INSERT INTO users (userName, userMail, userPassword) VALUES (?,?,?)");
            stmt.setString(1, userName);
            stmt.setString(2, email);
            stmt.setString(3, password);
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

    public void update(User user) {

    }

    public void delete(User user) {

    }

    public boolean getUserByUserNameAndPassword(String userName, String password) {
        System.out.println("UserDaoImpl:username:" + userName);
        System.out.println("UserDaoImpl: password:" + password);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            System.out.println("before rs block");
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM users WHERE userName=? AND userPassword=?");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            User user = null;
            if(rs.next()) {
                System.out.println("rs block");

//                user.setUserId(rs.getLong("userId"));
//                user.setUserName(rs.getString("userName"));
//                user.setUserMail(rs.getString("userMail"));
//                user.setUserPassword(rs.getString("userPassword"));

                return true;
            }

        }catch (SQLException e){
            e.getMessage();
        }
        finally {
            if(rs !=null){
                try{
                    rs.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
            if(stmt != null){
                try{
                    stmt.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
        }
        return false;

    }

    public User getUserById(long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT userId, userName, userMail FROM users WHERE userId = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            User user = null;
            if(rs.next()) {


                user.setUserId(rs.getLong("userId"));
                user.setUserName(rs.getString("userName"));
                user.setUserMail(rs.getString("userMail"));
            }
            return user;
        }catch (SQLException e){
            e.getMessage();
        }
        finally {
            if(rs !=null){
                try{
                    rs.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
            if(stmt != null){
                try{
                    stmt.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.getMessage();
                }
            }
        }

        return null;
    }
}
