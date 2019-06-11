package com.taxi.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    private static String url = "jdbc:mysql://192.168.0.105:3306/taxidb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String user = "vova";
    private static String password = "1234";

    public static Connection getConnection(){
        Connection connection = null;
        try{


            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, password);
//            return connection;
//        }catch (Exception e){
//            System.out.println("EXCEPTION: " + e.getStackTrace());
//            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
