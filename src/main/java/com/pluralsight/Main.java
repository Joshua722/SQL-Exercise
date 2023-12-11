package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/northwind";
        String user = "root";
        String password = "****";
        String query = "SELECT * FROM Products";

        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while(resultSet.next()){
                System.out.println(("Product ID : ") + resultSet.getString("ProductID"));
                System.out.println(("Product Name : ") + resultSet.getString("ProductName"));
                System.out.println(("Unit Price : ") + resultSet.getString("UnitPrice"));
                System.out.println(("Units in Stock : ") + resultSet.getString("UnitsInStock"));
                System.out.println("---------------------------------------------------------");
            }

            resultSet.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }



}
