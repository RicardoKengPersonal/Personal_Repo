package Lapr3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class orderSimulation {

    // Database connection utility
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/XE",
                "SYS as SYSDBA",
                "password"
        );
    }

    static List<Order> readOrdersCSV(String filePath) {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Skip the header row
            if ((line = br.readLine()) != null) {
                //System.out.println("Header detected and skipped: " + line);
            }

            // Process the rest of the file
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";"); // Use semicolon as the delimiter
                orders.add(new Order(
                        Integer.parseInt(values[0].trim()), // Order ID
                        Integer.parseInt(values[1].trim()), // Product ID
                        values[2].trim(),                  // Priority as String
                        Integer.parseInt(values[3].trim()) // Quantity
                ));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Error reading orders CSV: " + e.getMessage());
        }
        return orders;
    }


    // Fetch operation times from Oracle DB
    static Map<Integer, Integer> fetchOperationTimes() {
        Map<Integer, Integer> operationTimes = new HashMap<>();
        String query = "SELECT idOperation, expectedExecutionTime FROM operation";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                operationTimes.put(rs.getInt("idOperation"), rs.getInt("expectedExecutionTime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operationTimes;
    }

    // Simulate production
    static void simulateProduction(List<Order> orders, Map<Integer, Integer> operationTimes) {
        for (Order order : orders) {
            System.out.println("Simulating production for Order ID: " + order.getOrderId());
            Integer operationTime = operationTimes.get(order.getProductId());
            if (operationTime != null) {
                int totalTime = operationTime * order.getQuantity();
                System.out.println("  - Product ID: " + order.getProductId() + " | Quantity: " + order.getQuantity() + " | Priority: " + order.getPriority() + " | Total Time: " + totalTime + " units");
            } else {
                System.out.println("  - Operation time not found for Product ID: " + order.getProductId());
            }
        }
    }

}
