/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template


 */


package GUI;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *ideal_weight_db
 * @author banuk
 */
public class TestMySQLConnection {
      private static final String URL = "jdbc:mysql://localhost:3306/ideal_weight_db";
    private static final String USER = "root";  // Change to your MySQL username
    private static final String PASSWORD = "123456789";  // Change to your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
