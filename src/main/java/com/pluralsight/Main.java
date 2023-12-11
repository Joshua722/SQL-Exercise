package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/northwind";
        String user = "root";
        String password = "8371";
        String query = "SELECT * FROM Products";

        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while(resultSet.next()){
                System.out.println(resultSet.getString("ProductName"));
            }

            resultSet.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }



}
