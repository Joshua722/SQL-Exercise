package com.pluralsight;

import java.sql.*;
import java.util.Scanner;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static Connection conn = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    static BasicDataSource dataSource = new BasicDataSource();



    public static void main(String[] args) {
        String userInput;
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("****");
        do {
            System.out.println("""
                    What do you want to do?
                    1) Display all products
                    2) Display all customers
                    3) Display all categories
                    0) Exit
                    Please choose an option from above
                    """);
            userInput = myScanner.nextLine();
            switch (userInput) {
                case "1":
                    displayAllProducts();
                    break;
                case "2":
                    displayAllCustomers();
                    break;
                case "3":
                    displayAllCategories();
                    break;
                case "0":
                    System.exit(0);
            }

        } while (!userInput.equals("0"));
    }


    public static void displayAllProducts() {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Products");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(("Product ID : ") + resultSet.getString("ProductID"));
                System.out.println(("Product Name : ") + resultSet.getString("ProductName"));
                System.out.println(("Unit Price : ") + resultSet.getString("UnitPrice"));
                System.out.println(("Units in Stock : ") + resultSet.getString("UnitsInStock"));
                System.out.println("---------------------------------------------------------");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void displayAllCustomers() {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Customers ORDER BY Country");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(("CustomerID : ") + resultSet.getString("CustomerID"));
                System.out.println(("CompanyName : ") + resultSet.getString("CompanyName"));
                System.out.println(("City : ") + resultSet.getString("City"));
                System.out.println(("Country : ") + resultSet.getString("Country"));
                System.out.println(("Phone : ") + resultSet.getString("Phone"));
                System.out.println("---------------------------------------------------------");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void displayAllCategories() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Categories");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println("CategoryID : " + resultSet.getString("CategoryID"));
                System.out.println("CategoryName : " + resultSet.getString("CategoryName"));
                System.out.println("-------------------------------------------------------------------");
            }

            System.out.println("Please choose a CategoryID ");
            int userChoice = myScanner.nextInt();
            myScanner.nextLine();

            try (PreparedStatement preparedStatement1 = conn.prepareStatement("SELECT * FROM Products WHERE CategoryID = ?")) {
                preparedStatement1.setInt(1, userChoice);

                try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
                    while (resultSet1.next()) {
                        System.out.println("Product ID : " + resultSet1.getString("ProductID"));
                        System.out.println("Product Name : " + resultSet1.getString("ProductName"));
                        System.out.println("Unit Price : " + resultSet1.getString("UnitPrice"));
                        System.out.println("Units in Stock : " + resultSet1.getString("UnitsInStock"));
                        System.out.println("---------------------------------------------------------");
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
